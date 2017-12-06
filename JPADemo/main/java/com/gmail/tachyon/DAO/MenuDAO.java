package com.gmail.tachyon.DAO;

import com.gmail.tachyon.Entity.Dish;

import java.util.List;

public interface MenuDAO {
    void add(Dish dish);

    List<Dish> dishesPriceFromTo(double from, double to);

    List<Dish> discountsOnly();

    List<Dish> weightNoMoreThan(double weight, List<Dish> dishList);

}
