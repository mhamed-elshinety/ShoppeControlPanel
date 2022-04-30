package com.shenaitty.shoppe.listeners;

import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.ArrayList;

public interface OnGetProductsListener {
    void onGettingProducts(ArrayList<ProductModel> productModels);
}
