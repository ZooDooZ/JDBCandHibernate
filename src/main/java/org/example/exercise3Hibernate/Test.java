package org.example.exercise3Hibernate;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Test {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static EntityManager entityManager = sessionFactory.createEntityManager();

    public static void main(String[] args) {

        /**
         * SPRAWDZANIE WERSJI BAZY DANYCH
         */

//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        String sql = "Select version();";
//
//        String result = (String) session.createNativeQuery(sql).getSingleResult();
//        System.out.println("Connected!");
//        System.out.println(result);
//
//        session.close();
//        HibernateUtil.shutdown();

        /**
         * ENCJE - PROSTY CRUDs
         */




        // początek transmisji do bazy danych *************************************************************************
        entityManager.getTransaction().begin();

        // CRUD here
        // zapis encji - entityManager.persist(entity);
        // modyfikacje encji - entityManager.merge(entity);
        // usunięcie encji - entityManager.remove(entity);
        // wysukiwnie encji po id - entityManager.find(Entity.class(NazwaKlasy.class), 1(id));

        /**dodawanie pracownika do bazy danych*/
//        entityManager.persist(new Phone(EnumType.STRING, "3310i"));


        /**filtorwanie pracownika z bazy danych  i wyświetlanie informacje o nim */
//        Employee e1 = entityManager.find(Employee.class, 1);
//        Employee e2 = entityManager.find(Employee.class, 3);
//        System.out.println(e1);
//        System.out.println(e2);

        /** modyfikowanie pracownika z bazy danych */
        // 1 sposób - modyfikcja zwykłym seterem jego danych i dodatkowo wyświetlenie pracownika aby łatwiej było go modyfikować
//        Employee e1 = entityManager.find(Employee.class, 2);
//        Phone phone = new Phone();
//        e1.setPhone(phone);


        // 2 sposób - modyfikcja przez metode entityManage.merge(nazwaObiektu)
//        entityManager.merge(new Employee(1, "Hans", "Zimmer",
//                LocalDate.of(1969,10,12), "hanszimmer123@gmail.com"));

        /** usuwanie pracownika z bazy danych */
//        Employee e1 = entityManager.find(Employee.class, 1);
//        entityManager.remove(e1);

        /** dodawanie dwóch encji EMPLOYEE i PHONE */
//        Employee e1 = entityManager.find(Employee.class, 2);
//        Phone p1 = entityManager.find(Phone.class, 1);
//
//        e1.setPhone(p1);

        /** metoda tworząca dane w bazie danych */
//        initData();

        /** Wybierz z bazy właścicieli danej marki telefonów */
//        List<Employee> employeeList = findEmployeeWithThisPhone(Phone.PhoneBrand.SAMSUNG);
//
//        employeeList.forEach(e -> {
//            System.out.println(e);
//            System.out.println(e.getPhone());
//        });

        /** Usuń z bazy pracowników, którzy nie mają przypisanych zadań */
//        removeEmployeeWithoutTasks();

        /** Wyciągnij i wypisz z bazy wszystkie zadania o typie blocker, a następnie zaktualizuj ich
         tytuły tak, żeby zaczynały się od „BLOCKER!” */
//        updateBlockerTasks();

        /** Napisz program, który będzie wczytywać kolejne strony zadań (np. po 3 zadania) w
         zależności od tego, który numer strony poda użytkownik. */
        List<Task> tasks = findTasksByPagePer3(0);
        for(Task t : tasks){
            System.out.println(t);
        }



        // koniec transmisji do bazy danych *************************************************************************
        entityManager.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    private static List<Employee> findEmployeeWithThisPhone(Phone.PhoneBrand phoneBrand){
            EntityManager em = sessionFactory.createEntityManager();
            return em.createQuery("from Employee e join fetch e.phone p where p.phoneBrand = :param", Employee.class)
                    .setParameter("param", phoneBrand)
                    .getResultList();
    }

    private static void updateBlockerTasks(){
        EntityManager em = sessionFactory.createEntityManager();
        List<Task> tasks = em.createQuery("from Task t where t.taskType = :t", Task.class)
                .setParameter("t", Task.TaskType.BLOCKER)
                .getResultList();

        for(Task t : tasks){
            t.setTitle("BLOCKER! " + t.getTitle());
            entityManager.merge(t);
        }
    }

    private static List<Task> findTasksByPagePer3(int page){
        EntityManager em = sessionFactory.createEntityManager();
          Query query =  em.createQuery("from Task");
          query.setFirstResult(page * 3);
          query.setMaxResults(3);
            return query.getResultList();
    }

    private static void removeEmployeeWithoutTasks() {
        EntityManager em = sessionFactory.createEntityManager();
        int rowsChanged = em.createQuery("delete from Employee e where e.tasks is empty").executeUpdate();
        System.out.println("Rows changed: " + rowsChanged);
    }


    private static void initData() {
        Employee e1 = new Employee("Jan", "Kowalski", LocalDate.of(1977, 12, 6), "jkowalski@sda.com");
        Employee e2 = new Employee("Anna", "Nowak", LocalDate.of(1987, 10, 4), "anowak@onet.pl");
        Employee e3 = new Employee("Marek", "Kowal", LocalDate.of(1999, 5, 17), "marekk@gmail.com");

        Phone ph1 = new Phone(Phone.PhoneBrand.SAMSUNG, "s10");
        Phone ph2 = new Phone(Phone.PhoneBrand.NOKIA, "X1");
        Phone ph3 = new Phone(Phone.PhoneBrand.MOTOROLA, "123");


        e1.setPhone(ph1);
        e2.setPhone(ph2);
        e3.setPhone(ph3);

        Task t1 = new Task("Database schema", "Database schema creation", Task.TaskType.BLOCKER);
        Task t2 = new Task("Database script", "Creation of script for database generation", Task.TaskType.HIGH_PRIORITY);
        Task t3 = new Task("Security", "Security configuration", Task.TaskType.HIGH_PRIORITY);
        Task t4 = new Task("Login page", "[FRONTEND] Login page design", Task.TaskType.NORMAL);
        Task t5 = new Task("Login implementation", "User login backend implementation", Task.TaskType.NORMAL);
        Task t6 = new Task("Mail notifications", "Mail notifications implementation", Task.TaskType.LOW_PRIORITY);
        Task t7 = new Task("Password change", "[FRONTEND] Allow user to change password", Task.TaskType.LOW_PRIORITY);

        t1.setEmployee(e1);
        t2.setEmployee(e1);
        t3.setEmployee(e2);
        t4.setEmployee(e3);
        t5.setEmployee(e2);
        t6.setEmployee(e1);
        t7.setEmployee(e3);

        Project pr1 = new Project("Finance App");
        Project pr2 = new Project("Security");

        e1.setProjects(Arrays.asList(pr1));
        e2.setProjects(Arrays.asList(pr1, pr2));
        e3.setProjects(Arrays.asList(pr1));

        EntityManager em = sessionFactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(ph1);
        em.persist(ph2);
        em.persist(ph3);
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        em.persist(t5);
        em.persist(t6);
        em.persist(t7);
        em.persist(pr1);
        em.persist(pr2);

        em.getTransaction().commit();
    }
}
