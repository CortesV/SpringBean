package com.devcortes.hibernate;

import com.devcortes.hibernate.entity.Account;
import com.devcortes.hibernate.entity.Client;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test1(){
        Client client = sessionFactory.getCurrentSession().get(Client.class, 1);
        System.out.println("<- got client");
        client.getAccounts();
        System.out.println("<- got accounts");
        client.getAccounts().size();
        System.out.println("<- got accounts size");

        System.out.println("LOAD");
        sessionFactory.getCurrentSession().clear();

        Client client1 = sessionFactory.getCurrentSession().load(Client.class, 1);
        System.out.println("<- got client");
        client1.getAccounts();
        System.out.println("<- got accounts");
        client1.getAccounts().size();
        System.out.println("<- got accounts size");
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test2(){
        Account account = sessionFactory.getCurrentSession().get(Account.class, 1);
        System.out.println("<- got account");
        account.getClient();
        System.out.println("<- got client");
        account.getClient().getId();
        System.out.println("<- got client's id");
        System.out.println(account.getClient().getId());

        System.out.println("LOAD");
        sessionFactory.getCurrentSession().clear();

        Account account1 = sessionFactory.getCurrentSession().load(Account.class, 1);
        System.out.println("<- got account");
        account1.getClient();
        System.out.println("<- got client");
        account1.getClient().getId();
        System.out.println("<- got client's id");
        System.out.println(account1.getClient().getId());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test3(){
        Client client = sessionFactory.getCurrentSession().load(Client.class, 1);
        System.out.println("<- got client");
        client.getAge();
        System.out.println("<- got clients age");
        System.out.println(client.getAge());

        System.out.println("LOAD");
        sessionFactory.getCurrentSession().clear();

        Client client1 = sessionFactory.getCurrentSession().get(Client.class, 1);
        System.out.println("<- got client");
        client1.getAge();
        System.out.println("<- got clients age");
        System.out.println(client1.getAge());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testNplusOneProblem(){
        List<Client> clients = sessionFactory.getCurrentSession().createQuery("select c from Client c").list();
        clients.forEach(client -> client.getAccounts().size());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testExtraLazy(){
        Client client = sessionFactory.getCurrentSession().get(Client.class, 1);
        client.getAccounts().size();
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void testMergeCollections(){
        Client client = sessionFactory.getCurrentSession().get(Client.class, 1);
        //client.setAccounts(new ArrayList<>());
        client.getAccounts().clear();
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void testDirtyChecking(){
        Account account = sessionFactory.getCurrentSession().get(Account.class, 5);
        account.setAmount(1233);

        Client client = sessionFactory.getCurrentSession().get(Client.class, 1);
    }
}
