package com.gmail.tachyon.DAO;

import com.gmail.tachyon.Entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO{
    private EntityManager em;

    public MenuDAOImpl(EntityManager em) {
        this.em = em;

    }

    public void add(Dish dish) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(dish);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List<Dish> dishesPriceFromTo(double from, double to) {
        TypedQuery<Dish> typedQuery = em.createQuery(
                "SELECT m FROM Dish m WHERE m.price>=:from AND m.price<=:to", Dish.class);
        typedQuery.setParameter("from", from);
        typedQuery.setParameter("to", to);
        return weightNoMoreThan(1d, typedQuery.getResultList());
    }

    public List<Dish> discountsOnly() {
        TypedQuery<Dish> typedQuery = em.createQuery(
                "SELECT m FROM Dish m WHERE m.discount=true", Dish.class);
        return weightNoMoreThan(1d, typedQuery.getResultList());
    }

    public List<Dish> weightNoMoreThan(double weight, List<Dish> dishList) {
        List<Dish> resultList = new ArrayList<>();
        double currentWeight = 0;
        for (Dish dish : dishList) {
            if (currentWeight + dish.getWeight() <= weight) {
                currentWeight += dish.getWeight();
                resultList.add(dish);
            } else {
                return resultList;
            }
        }
        return resultList;
    }

}
