package com.hibernate;

import com.hibernate.converter.BirthdayConverter;
import com.hibernate.entity.User;
import com.hibernate.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {

        User user = User.builder()
                .username("ivan@gmail.com")
                .lastname("Ivanov")
                .firstname("Ivan")
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }
            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                user.setFirstname("Sveta");
//                session2.delete(user);

                // refresh
//                User freshUser = session2.get(User.class, user.getUsername());
//                user.setLastname(freshUser.getLastname());
//                user.setFirstname(freshUser.getFirstname());
//
//                session2.refresh(user);

                // merge
//                User freshUser = session2.get(User.class, user.getUsername());
//                freshUser.setLastname(user.getLastname());
//                freshUser.setFirstname(user.getFirstname());

                Object mergedUser = session2.merge(user);

                session2.getTransaction().commit();
            }
        }
    }
}
