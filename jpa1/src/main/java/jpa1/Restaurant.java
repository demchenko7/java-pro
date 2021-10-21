package jpa1;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add dish");
                    System.out.println("2: choose dishes with discount");
                    System.out.println("3: choose dishes from a range of prices");
                    System.out.println("4: choose dishes with a total weight < 1 kg");
                    System.out.println("5: view menu");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addDish(sc);
                            break;
                        case "2":
                            selectDiscounts(sc);
                            break;
                        case "3":
                            selectCosts(sc);
                            break;
                        case "4":
                            selectWeight(sc);
                            break;
                        case "5":
                            viewMenu();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addDish(Scanner sc) {
        System.out.print("Enter dish name: ");
        String name = sc.nextLine();

        System.out.print("Enter dish cost: ");
        String sCost = sc.nextLine();
        int cost = Integer.parseInt(sCost);

        System.out.print("Enter dish weight(g): ");
        String sWeight = sc.nextLine();
        if(sWeight.endsWith("g")) sWeight = sWeight.substring(0,sWeight.length() - 1).trim();
        int weight = Integer.parseInt(sWeight);

        System.out.print("Enter dish discount(%): ");
        String sDiscount = sc.nextLine();
        if(sDiscount.endsWith("%")) sDiscount = sDiscount.substring(0,sDiscount.length() - 1).trim();
        int discount = Integer.parseInt(sDiscount);

        em.getTransaction().begin();
        try {
            Menu m = new Menu(name, cost, weight, discount);
            em.persist(m);
            em.getTransaction().commit();

            System.out.println(m.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void selectDiscounts(Scanner sc) {
        try {
            Query query = em.createQuery(
                    "SELECT m FROM Menu m WHERE m.discount is not null order by m.discount desc", Menu.class);

            List<Menu> list = (List<Menu>) query.getResultList();

            for (Menu m : list)
                System.out.println(m);

        } catch (NoResultException ex) {
            System.out.println("Discounts not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

    }

    private static void selectCosts(Scanner sc) {
        System.out.print("Enter starting price: ");
        String sStart = sc.nextLine();
        int startCost = Integer.parseInt(sStart);

        System.out.print("Enter final price: ");
        String sEnd = sc.nextLine();
        int endCost = Integer.parseInt(sEnd);

        try {
            Query query = em.createQuery(
                    "SELECT m FROM Menu m WHERE m.cost >= :startCost and m.cost <= :endCost order by m.cost", Menu.class);

            List<Menu> list = (List<Menu>) query.getResultList();

            for (Menu m : list)
                System.out.println(m);

        } catch (NoResultException ex) {
            System.out.println("Discounts not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }
    }

    private static void selectWeight(Scanner sc) {
         try {
            Query query = em.createQuery(
                    "SELECT m FROM Menu m WHERE m.weight < 1000  order by m.weight", Menu.class);
             List<Menu> list = (List<Menu>) query.getResultList();

             //
             List<Menu> listSum = null;
             int sum = 0;
             for (int i = 0; i < list.size(); i++) {
                 Menu m = list.get(i);
                 sum = sum + m.getWeight();
                 if(sum < 1000 )
                     listSum.add(m);
                 else
                     break;
             }
             if(listSum != null && listSum.size() > 0) {
                 for (Menu m : listSum) {
                     System.out.println(m);
                 }
             }

        } catch (NoResultException ex) {
            System.out.println("Menu not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

    }

    private static void viewMenu() {
        Query query = em.createQuery(
                "SELECT v FROM Menu v", Menu.class);
        List<Menu> list = (List<Menu>) query.getResultList();

        for (Menu m : list)
            System.out.println(m);
    }


}
