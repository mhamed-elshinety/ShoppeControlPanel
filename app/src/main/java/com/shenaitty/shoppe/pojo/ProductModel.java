package com.shenaitty.shoppe.pojo;

import android.graphics.Bitmap;

import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.listeners.OnGetProductsListener;

import java.util.ArrayList;

public class ProductModel {
    private String id, name, description, category, moreInfo, shipperName;
    private float price, discount, widthInCM, lengthInCM,heightInCM;
    private int quantity;
    private ArrayList<Bitmap> productImages;
    private OnGetProductsListener onGetProductsListener;

    public ProductModel(OnGetProductsListener onGetProductsListener){
        this(Constants.NA,Constants.NA,Constants.NA, Constants.OTHER,Constants.NA,Constants.NA,0,0,0,0,0,0,null);
        this.onGetProductsListener = onGetProductsListener;
    }

    public ProductModel(String id, String name, String description, String moreInfo, float price, float discount, int quantity) {
        this(id,name,description, Constants.OTHER,moreInfo,Constants.NA,price,discount,0,0,0,quantity,null);
    }

    public ProductModel(String id, String name, String description, String category, String moreInfo, String shipperName, float price, float discount, float widthInCM, float lengthInCM, float heightInCM, int quantity, ArrayList<Bitmap> productImages) {
        setId(id);
        setName(name);
        setDescription(description);
        setCategory(category);
        setMoreInfo(moreInfo);
        setShipperName(shipperName);
        setPrice(price);
        setDiscount(discount);
        setWidthInCM(widthInCM);
        setHeightInCM(heightInCM);
        setLengthInCM(lengthInCM);
        setQuantity(quantity);
        setProductImages(productImages);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if(price>0)
            this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        if(discount>0)
            this.discount = discount;

    }

    public float getWidthInCM() {
        return widthInCM;
    }

    public void setWidthInCM(float widthInCM) {
        if(widthInCM>1)
            this.widthInCM = widthInCM;
    }

    public float getLengthInCM() {
        return lengthInCM;
    }

    public void setLengthInCM(float lengthInCM) {
        if (lengthInCM>1)
            this.lengthInCM = lengthInCM;
    }

    public float getHeightInCM() {
        return heightInCM;
    }

    public void setHeightInCM(float heightInCM) {
        if(heightInCM>1)
            this.heightInCM = heightInCM;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity>0)
            this.quantity = quantity;
    }

    public ArrayList<Bitmap> getProductImages() {
        return productImages;
    }

    public void setProductImages(ArrayList<Bitmap> productImages) {
        this.productImages = productImages;
    }

    public void getProductsList(){
        //TODO: Getting data from API
        onGetProductsListener.onGettingProducts(getDummyData());
    }

    private ArrayList<ProductModel> getDummyData(){
        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        products.add(new ProductModel("1", "Lira Earrings","Ring","Black/Meduim",12.5f,0,5));
        return products;
    }
}
