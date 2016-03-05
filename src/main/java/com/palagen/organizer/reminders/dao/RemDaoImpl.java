package com.palagen.organizer.reminders.dao;

import com.palagen.organizer.reminders.model.Rem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("remDao")
public class RemDaoImpl implements RemDao {

    private static final Log LOG = LogFactory.getLog(RemDaoImpl.class);
    private SessionFactory sessionFactory;

    @Override
    public List<Rem> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select r from Rem r").list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}