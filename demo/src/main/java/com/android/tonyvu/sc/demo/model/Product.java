package com.android.tonyvu.sc.demo.model;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.tonyvu.sc.model.Addons;
import com.android.tonyvu.sc.model.Saleable;

public class Product implements Saleable, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    private int pId;
    private String pName;
    private BigDecimal pPrice;
    private String pDescription;
    private String pImageName;



    private int quantity;

    public List<List<Addons>> getAddonsList() {
        return addonsList;
    }

    public void setAddonsList(List<List<Addons>> addonsList) {
        this.addonsList = addonsList;
    }

    private List<List<Addons>> addonsList;
    private Map<String, Addons> totalAddons = new HashMap<String, Addons>();

    /**
     ************* save list of all addons
     * @param addons
     */
    public void addons(Addons addons)
    {
        if(totalAddons.containsKey(String.valueOf(addons.addonsId)))
        {
            Addons addons1=totalAddons.get(String.valueOf(addons.addonsId));
            addons1.quantity+=1;
        }
        else
        {
            totalAddons.put(String.valueOf(addons.addonsId),addons);
        }
    }

    public Map<String, Addons> getAllAddonsList()
    {
        return totalAddons;
    }
    public Product() {
        super();
    }

    public Product(int pId, String pName, BigDecimal pPrice, String pDescription, String pImageName,List<List<Addons>> addonsList) {
        setId(pId);
        setName(pName);
        setPrice(pPrice);
        setDescription(pDescription);
        setImageName(pImageName);
        setAddonsList(addonsList);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;

        boolean addons=true;
        List<List<Addons>> firstList=((Product) o).getAddonsList();
        List<List<Addons>> secondList=((Product) this).getAddonsList();
        if(firstList.size()==0&&secondList.size()==0)
        {
            addons=false;
        }
        else if(firstList.get(firstList.size()-1).size()==secondList.get(secondList.size()-1).size())
        {
            for(int i=0;i<firstList.get(firstList.size()-1).size();i++)
            {
                if(firstList.get(firstList.size()-1).get(i).addonsId!=secondList.get(secondList.size()-1).get(i).addonsId)
                {
                    addons=false;
                    break;
                }
            }
        }
        else
        {
            addons=false;
        }
        boolean status=((this.pId == ((Product) o).getId())&&addons);
        if(true)
        {
            addonsList.add(secondList.get(secondList.size()-1));

        }
        return status;
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (pName == null ? 0 : pName.hashCode());
        hash = hash * prime + (pPrice == null ? 0 : pPrice.hashCode());
        hash = hash * prime + (pDescription == null ? 0 : pDescription.hashCode());

        return hash;
    }


    public int getId() {
        return pId;
    }

    public void setId(int id) {
        this.pId = id;
    }

    @Override
    public BigDecimal getPrice() {
        return pPrice;
    }

    @Override
    public String getName() {
        return pName;
    }

    @Override
    public List<Addons> getAddonsItems() {
        if(addonsList!=null)
        return addonsList.get(0);
        else
            return null;
    }

    @Override
    public void addAddons(List<Addons> list) {
        if(list!=null) {
            addonsList.add(list);
            for(Addons addons:list)
            {
                addons(addons);
            }
        }
        }

    @Override
    public List<Addons> getLastAddonsItems() {
        if(addonsList!=null)
            return addonsList.get(addonsList.size()-1);
        else
            return null;
    }

    @Override
    public void incrementQuantityByOne() {
        quantity++;
    }

    @Override
    public void deccrementQuantityByOne() {
        if(quantity>0)
        {
            quantity--;
        }
    }

    @Override
    public void removeAddons() {
       if(addonsList.size()>0)
       {
          for(Addons addons:addonsList.get(addonsList.size()-1))
          {
           removeaddonFromList(addons);
          }
           addonsList.remove(addonsList.size()-1);

       }
    }

    private void removeaddonFromList(Addons addons)
    {
        if(totalAddons.containsKey(String.valueOf(addons.addonsId)))
        {
            Addons addons1=totalAddons.get(String.valueOf(addons.addonsId));
            if(addons1.quantity==1)
            {
                totalAddons.remove(String.valueOf(addons.addonsId));
            }
            else
            {
                addons1.quantity=addons1.quantity-1;
            }

        }
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getProductId() {
        return String.valueOf(pId);
    }

    public void setPrice(BigDecimal price) {
        this.pPrice = price;
    }

    public void setName(String name) {
        this.pName = name;
    }

    public String getDescription() {
        return pDescription;
    }

    public void setDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getImageName() {
        return pImageName;
    }

    public void setImageName(String imageName) {
        this.pImageName = imageName;
    }


}
