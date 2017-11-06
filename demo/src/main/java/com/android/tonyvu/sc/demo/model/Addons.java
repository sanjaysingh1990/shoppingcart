package com.android.tonyvu.sc.demo.model;

import com.android.tonyvu.sc.model.SaleableAddon;

import java.io.Serializable;
import java.math.BigDecimal;

public  class Addons implements SaleableAddon,Serializable
    {
        public int addonsId;
        public String addonName;
        public BigDecimal addonPrice;
        public int quantity=1;

        public Addons()
        {

        }
        public Addons(int addonsId,String addonName,BigDecimal addonPrice)
        {
            this.addonsId=addonsId;
            this.addonName=addonName;
            this.addonPrice=addonPrice;
        }

        @Override
        public BigDecimal getPrice() {
            return addonPrice;
        }

        @Override
        public String getName() {
            return addonName;
        }

        @Override
        public int getQuantity() {
            return quantity;
        }
    }