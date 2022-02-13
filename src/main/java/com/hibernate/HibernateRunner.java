package com.hibernate;

import com.hibernate.entity.Payment;
import com.hibernate.entity.Profile;
import com.hibernate.entity.User;
import com.hibernate.util.HibernateUtil;
import com.hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);
//            session.doWork(connection -> connection.setAutoCommit(false));

//            session.beginTransaction();

            var profile = Profile.builder()
                    .user(session.find(User.class, 1L))
                    .language("ru")
                    .street("Kolasa 28")
                    .build();
            session.save(profile);

            var payments = session.createQuery("select p from Payment  p", Payment.class)
//                    .setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
//                    .setHint("javax.persistence.lock.timeout", 5000)
//                    .setReadOnly(true)
//                    .setHint(QueryHints.READ_ONLY, true)
                    .list();

            var payment = session.find(Payment.class, 1L);
            payment.setAmount(payment.getAmount() + 10);

//            session.getTransaction().commit();
        }
    }
}

