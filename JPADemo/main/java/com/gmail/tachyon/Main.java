package com.gmail.tachyon;

import com.gmail.tachyon.DAO.MenuDAOImpl;
import com.gmail.tachyon.Entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo");
        EntityManager em = emf.createEntityManager();
        MenuDAOImpl ms = new MenuDAOImpl(em);

        insertValues(ms);

        List<Dish> dishList = ms.discountsOnly();
        printList(dishList, "Discounts only:");

        dishList = ms.dishesPriceFromTo(25d, 45d);
        printList(dishList, "Dishes with price from 25 to 45:");

        em.close();
        emf.close();

    }

    public static void printList(List<Dish> dishList, String message) {
        System.out.println(message);
        if (dishList.size() != 0) {
            dishList.forEach(System.out::println);
        } else {
            System.out.println("No matches!");
        }
    }

    public static void insertValues(MenuDAOImpl ms) {
        Dish dish1 = new Dish();
        dish1.setName("Olivye");
        dish1.setPrice(30.0d);
        dish1.setDiscount(true);
        dish1.setWeight(0.2d);
        ms.add(dish1);

        Dish dish2 = new Dish();
        dish2.setName("Shuba");
        dish2.setPrice(40.0d);
        dish2.setDiscount(true);
        dish2.setWeight(0.25d);
        ms.add(dish2);

        Dish dish3 = new Dish();
        dish3.setName("Mandaryny");
        dish3.setPrice(45.0d);
        dish3.setDiscount(false);
        dish3.setWeight(0.3d);
        ms.add(dish3);

        Dish dish4 = new Dish();
        dish4.setName("Zharkoe");
        dish4.setPrice(50.0d);
        dish4.setDiscount(false);
        dish4.setWeight(0.4d);
        ms.add(dish4);

    }
}
