package com.palagen.organizer.reminders.dao;

import com.palagen.organizer.reminders.model.Rem;
import com.palagen.organizer.users.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class RemDaoImpl implements RemDao {

    @Autowired
    private SessionFactory sessionFactory;

    public RemDaoImpl() {

    }

    public RemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Rem> findAll(long user_id) {
        return sessionFactory.getCurrentSession()
                .createQuery("select r from Rem r where owner=" + user_id).list();
    }

    @Override
    @Transactional
    public Rem get(int id) {
        String hql = "from Rem where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        @SuppressWarnings("unchecked")
        List<Rem> listReminders = (List<Rem>) query.list();

        if (listReminders != null && !listReminders.isEmpty()) {
            return listReminders.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(int id) {

        Rem reminder = get(id);

        if (!reminder.equals(null))
        {
            sessionFactory.getCurrentSession().delete(reminder);
        }

    }

    @Override
    @Transactional
    public void update(Rem rem) {

        Date rightNow = new Date();
        rem.setDate(rightNow);

        sessionFactory.getCurrentSession().saveOrUpdate(rem);
    }
}