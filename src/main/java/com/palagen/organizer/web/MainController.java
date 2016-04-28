package com.palagen.organizer.web;

import com.palagen.organizer.reminders.dao.RemDao;
import com.palagen.organizer.reminders.model.Rem;
import com.palagen.organizer.users.dao.UserDao;
import com.palagen.organizer.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private RemDao remDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = { "/", "/welcome**" , "/index**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;

    }

    @RequestMapping(value = { "/reminders" }, method = RequestMethod.GET)
    public ModelAndView reminders(HttpServletRequest request) {

        List<Rem> listRem = new ArrayList<Rem>();
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        if (principal!=null && !username.isEmpty())
        {
            User user = userDao.findByUserName(username);
            listRem = remDao.findAll(user.getId());
        }

        ModelAndView model = new ModelAndView();
        model.addObject("reminders", listRem);
        model.setViewName("reminders");
        return model;

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){

        ModelAndView model = new ModelAndView("register");
        model.addObject("user", new User());
        model.setViewName("register");

        return model;

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) throws Exception{

        String error;

        try {
            userDao.create(user);
            return new ModelAndView("redirect:/login");
        } catch (Exception e)
        {
            error = "Пользователь с таким именем уже зарегистрирован!";
            ModelAndView model = new ModelAndView();
            model.addObject("error", error);
            model.setViewName("register");
            return model;
        }

    }

    @RequestMapping(value = "/addReminder", method = RequestMethod.GET)
    public ModelAndView newRem() {
        ModelAndView model = new ModelAndView("reminderAddForm");
        model.addObject("reminder", new Rem());
        return model;
    }

    @RequestMapping(value = "/editReminder", method = RequestMethod.GET)
    public ModelAndView editReminder(HttpServletRequest request) {
        int remId = Integer.parseInt(request.getParameter("id"));
        Rem rem = remDao.get(remId);
        ModelAndView model = new ModelAndView("reminderForm");
        model.addObject("reminder", rem);
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(HttpServletRequest request) {
        Rem reminder;
        int remId = Integer.parseInt(request.getParameter("dataId"));
        String theme = request.getParameter("theme");
        String date  = request.getParameter("date");

        Rem rem = remDao.get(remId);

        if (rem == null)
        {
            reminder = new Rem();

            Principal principal = request.getUserPrincipal();
            String username = principal.getName();
            User user = userDao.findByUserName(username);
            reminder.setOwner((int)user.getId());
        } else {
            reminder = rem;
        }

        reminder.setTheme(theme);

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reminder.setDate(format.parse(date));

        } catch (Exception e) {
            e.printStackTrace();
        }

        remDao.update(reminder);
        return new ModelAndView("redirect:/reminders");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteReminder(HttpServletRequest request) {
        int remId = Integer.parseInt(request.getParameter("id"));
        remDao.delete(remId);
        return new ModelAndView("redirect:/reminders");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "Выход успешно осуществлен.");
        }
        model.setViewName("login");

        return model;

    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Неверное имя пользователя или пароль!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Неверное имя пользователя или пароль!";
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

}
