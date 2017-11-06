package com.android.tonyvu.sc.model;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.tonyvu.sc.exception.ProductNotFoundException;
import com.android.tonyvu.sc.exception.QuantityOutOfRangeException;

/**
 * A representation of shopping cart.
 * <p/>
 * A shopping cart has a map of {@link Saleable} products to their corresponding quantities.
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 42L;

    private Map<String, Saleable> cartItemMap = new HashMap<String, Saleable>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int totalQuantity = 0;

    /**
     * Add a quantity of a certain {@link Saleable} product to this shopping cart
     *
     * @param sellable the product will be added to this shopping cart
     * @param quantity the amount to be added
     */
//    public void add(final Saleable sellable, int quantity) {
//        if (cartItemMap.containsKey(sellable)) {
//            cartItemMap.put(sellable, cartItemMap.get(sellable) + quantity);
//        } else {
//            cartItemMap.put(sellable, quantity);
//        }
//
//        totalPrice = totalPrice.add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
//        totalQuantity += quantity;
//    }

    public void add(String orderId,final Saleable sellable) {
        if (cartItemMap.containsKey(orderId)) {
             //get the saved object increase the quantity and add addons list to it and check previous addon is same or not
            //cartItemMap.put(sellable, cartItemMap.get(sellable) + quantity);
            Saleable saleable=cartItemMap.get(orderId);
            saleable.incrementQuantityByOne();
            saleable.addAddons(sellable.getAddonsItems());


        } else
            {
                sellable.incrementQuantityByOne();
               cartItemMap.put(orderId,sellable);
        }

        totalPrice = totalPrice.add(sellable.getPrice().multiply(BigDecimal.valueOf(1)));
        totalPrice=totalPrice.add(getAddonsPrice(sellable.getAddonsItems())); //add addon price
        totalQuantity += 1;
    }


    /**
     * Set new quantity for a {@link Saleable} product in this shopping cart
     *
     * @param sellable the product which quantity will be updated
     * @param quantity the new quantity will be assigned for the product
     * @throws ProductNotFoundException    if the product is not found in this shopping cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
//    public void update(final Saleable sellable, int quantity) throws ProductNotFoundException, QuantityOutOfRangeException {
//        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();
//        if (quantity < 0)
//            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");
//
//        int productQuantity = cartItemMap.get(sellable);
//        BigDecimal productPrice = sellable.getPrice().multiply(BigDecimal.valueOf(productQuantity));
//
//        cartItemMap.put(sellable, quantity);
//
//        totalQuantity = totalQuantity - productQuantity + quantity;
//        totalPrice = totalPrice.subtract(productPrice).add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
//    }

    /**
     * Remove a certain quantity of a {@link Saleable} product from this shopping cart
     *
     * @param sellable the product which will be removed
     * @param quantity the quantity of product which will be removed
     * @throws ProductNotFoundException    if the product is not found in this shopping cart
     * @throws QuantityOutOfRangeException if the quantity is negative or more than the existing quantity of the product in this shopping cart
     */
//    public void remove(final Saleable sellable, int quantity) throws ProductNotFoundException, QuantityOutOfRangeException {
//        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();
//
//        int productQuantity = cartItemMap.get(sellable);
//
//        if (quantity < 0 || quantity > productQuantity)
//            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative and less than the current quantity of the product in the shopping cart.");
//
//        if (productQuantity == quantity) {
//            cartItemMap.remove(sellable);
//        } else {
//            cartItemMap.put(sellable, productQuantity - quantity);
//        }
//
//        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
//        totalQuantity -= quantity;
//    }

    /**
     * Remove a {@link Saleable} product from this shopping cart totally
     *
     * @param sellable the product to be removed
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    public void remove(final Saleable sellable) throws ProductNotFoundException {


        if (!cartItemMap.containsKey(sellable.getProductId())) throw new ProductNotFoundException();
        BigDecimal addonprice=BigDecimal.ZERO;
        int quantity = cartItemMap.get(sellable.getProductId()).getQuantity();
        if(quantity==1) {
            addonprice=addonprice.add(getAddonsPrice(sellable.getLastAddonsItems()));
            sellable.removeAddons();
            cartItemMap.remove(sellable.getProductId());
        }
        else {

           Saleable saleable=cartItemMap.get(sellable.getProductId());
           saleable.deccrementQuantityByOne();
           addonprice=addonprice.add(getAddonsPrice(sellable.getLastAddonsItems()));
           saleable.removeAddons();
           quantity=1;
        }
        Log.e("quantity",quantity+"");
        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalPrice=totalPrice.subtract(addonprice); //add addon price

        totalQuantity -= quantity;
    }

    private BigDecimal getAddonsPrice(List<Addons> list)
    {
         if(list==null) {
             return new BigDecimal(0.0); //
         }

         BigDecimal totaladdonPrice = BigDecimal.ZERO;

        for(Addons addons:list)
        {
          totaladdonPrice=totaladdonPrice.add(addons.addonPrice);
        }
        Log.e("totaladdonprice",totaladdonPrice.toString());
        return totaladdonPrice;
    }
    /**
     * Remove all products from this shopping cart
     */
    public void clear() {
        cartItemMap.clear();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    /**
     * Get quantity of a {@link Saleable} product in this shopping cart
     *
     * @param sellable the product of interest which this method will return the quantity
     * @return The product quantity in this shopping cart
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
//    public int getQuantity(final Saleable sellable) throws ProductNotFoundException {
//        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();
//        return cartItemMap.get(sellable);
//    }

    /**
     * Get total cost of a {@link Saleable} product in this shopping cart
     *
     * @param sellable the product of interest which this method will return the total cost
     * @return Total cost of the product
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    public BigDecimal getCost(final Saleable sellable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(sellable.getProductId())) throw new ProductNotFoundException();
        return sellable.getPrice().multiply(BigDecimal.valueOf(cartItemMap.get(sellable.getProductId()).getQuantity()));
    }

    /**
     * Get total price of all products in this shopping cart
     *
     * @return Total price of all products in this shopping cart
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Get total quantity of all products in this shopping cart
     *
     * @return Total quantity of all products in this shopping cart
     */
    public int getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * Get set of products in this shopping cart
     *
     * @return Set of {@link Saleable} products in this shopping cart
     */
//    public Set<Saleable> getProducts() {
//        return cartItemMap.keySet();
//    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public Map<String, Saleable> getItemWithQuantity() {
        Map<String, Saleable> cartItemMap = new HashMap<String,Saleable>();
        cartItemMap.putAll(this.cartItemMap);
        return cartItemMap;
    }

    @Override
    public String toString() {
//        StringBuilder strBuilder = new StringBuilder();
//        for (Entry<Saleable, Integer> entry : cartItemMap.entrySet()) {
//            strBuilder.append(String.format("Product: %s, Unit Price: %f, Quantity: %d%n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue()));
//
//        }
//        strBuilder.append(String.format("Total Quantity: %d, Total Price: %f", totalQuantity, totalPrice));
//
//        return strBuilder.toString();
        return "";
    }
}
