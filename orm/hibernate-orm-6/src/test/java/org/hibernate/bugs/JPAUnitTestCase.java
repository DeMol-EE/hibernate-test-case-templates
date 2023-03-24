package org.hibernate.bugs;

import foo.AccountType;
import foo.Organisation;
import foo.OrganisationUser;
import foo.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void hhh16366() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // seed data
        entityManager.getTransaction().begin();
        Organisation organisation = new Organisation();
        entityManager.persist(organisation);
        User user = new User();
        user.setAccountType(AccountType.FOO);
        entityManager.persist(user);
        OrganisationUser organisationUser = new OrganisationUser();
        organisationUser.setOrganisation(organisation);
        organisationUser.setUser(user);
        entityManager.persist(organisationUser);
        entityManager.getTransaction().commit();
        // broken query:
        entityManager.createQuery(
                        "select distinct o from OrganisationUser o join fetch o.user u where o.organisation.id = ?1 and u.accountType = ?2 ",
                        OrganisationUser.class)
                .setParameter(1, 1)
                .setParameter(2, AccountType.FOO)
                .getResultList();
        entityManager.close();
    }
}
