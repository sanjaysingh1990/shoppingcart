package com.android.tonyvu.sc.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implements this interface for any product which can be added to shopping cart
 */
public interface Saleable {
    BigDecimal getPrice();

    String getName();

    List<Addons> getAddonsItems();
    void addAddons(List<Addons> list);
    List<Addons> getLastAddonsItems();

    void incrementQuantityByOne();
    void deccrementQuantityByOne();
    void removeAddons();
    int getQuantity();
    String getProductId();

}
