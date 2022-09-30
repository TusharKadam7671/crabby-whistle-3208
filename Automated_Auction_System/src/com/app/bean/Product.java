package com.app.bean;

public class Product {
    
    private int prodid;
    private String name;
    private String category;
    private float minprice;
    private int quantity;
    
    public Product()
    {
        
    }

    public Product(int prodid, String name, String category, float minprice, int quantity) {
        super();
        this.prodid = prodid;
        this.name = name;
        this.category = category;
        this.minprice = minprice;
        this.quantity = quantity;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getMinprice() {
        return minprice;
    }

    public void setMinprice(float minprice) {
        this.minprice = minprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [prodid=" + prodid + ", name=" + name + ", category=" + category + ", minprice=" + minprice
                + ", quantity=" + quantity + "]";
    }
    
    

}
