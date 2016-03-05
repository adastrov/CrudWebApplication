package com.palagen.organizer.reminders.service;

import com.palagen.organizer.reminders.dao.RemDao;
import com.palagen.organizer.reminders.model.Rem;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringHibernateSample {

    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
        ctx.refresh();
        RemDao remDao = ctx.getBean("remDao", RemDao.class);
        listReminders(remDao.findAll());
    }

    private static void listReminders(List<Rem> rems) {
        System.out.println("");
        System.out.println("Listing contacts without details:");
        for (Rem rem : rems) {
            System.out.println(rem.getTheme());
            System.out.println();
        }
    }
}