package com.android.tonyvu.sc.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implements this interface for any product which can be added to shopping cart
 */
public interface Saleable {
    BigDecimal getPrice();

    String getName();
    int getQuantity();
    List<Saleable> getAddonsItems();
    void addAddons(List<Saleable> list);
    List<Saleable> getLastAddonsItems();

    void incrementQuantityByOne();
    void deccrementQuantityByOne();
    void removeAddons();

    String getProductId();

}
