package com.devcortes.hibernate;

import com.devcortes.hibernate.entity.Account;
import com.devcortes.hibernate.entity.Client;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ClientAccountTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testOneToMany(){
        List<Client> clients = (List<Client>) sessionFactory.getCurrentSession().createQuery("from Client ").list();
        for (Client client : clients) {
            int size = client.getAccounts().size();
            System.out.println(size);
        }
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testManyToOne(){
        List<Account> accounts = (List<Account>) sessionFactory.getCurrentSession().createQuery("from Account ").list();
        for (Account account : accounts) {
            System.out.println(account.getClient().getId());
        }
    }
}
