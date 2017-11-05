package com.android.tonyvu.sc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public  class Addons implements Serializable
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

    }