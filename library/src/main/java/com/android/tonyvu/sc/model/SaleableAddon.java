package com.android.tonyvu.sc.model;

import java.math.BigDecimal;

/**
 * Created by android on 6/11/17.
 */

public interface SaleableAddon {
    BigDecimal getPrice();

    String getName();

    int getQuantity();
}
