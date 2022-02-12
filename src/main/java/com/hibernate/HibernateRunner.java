package com.hibernate;

import com.hibernate.entity.User;
import com.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var checkUser = session.get(User.class, 1L);

            session.getTransaction().commit();
        }
    }
}

