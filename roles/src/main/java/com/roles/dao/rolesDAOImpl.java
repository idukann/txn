package com.roles.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by vaibhav.gupta1 on 12/18/2018.
 */
public class rolesDAOImpl {


    @Repository
    public class AccountDAOImpl implements rolesDAO.AccountDAO {
        final  Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);
        private SessionFactory sessionFactory;
        @Autowired
        public AccountDAOImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }
        @Override
        public Account findById(int id) {
            Session session = this.sessionFactory.getCurrentSession();
            TypedQuery<Account> query = session.getNamedQuery("findAccountById");
            query.setParameter("id", id);
            Account account = query.getSingleResult();
            return account;
        }
    }
}
