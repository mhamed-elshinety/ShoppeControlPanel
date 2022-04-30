package com.shenaitty.shoppe.ui;

import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.ArrayList;

public interface HomeFragmentView {
    void onGettingProducts(ArrayList<ProductModel> products);
}
