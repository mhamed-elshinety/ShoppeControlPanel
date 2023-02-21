package com.shenaitty.shoppe.ui;

import com.shenaitty.shoppe.listeners.OnGetProductsListener;
import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.ArrayList;

public class HomeFragmentPresenter implements OnGetProductsListener {

    private HomeFragmentView view;
    private ProductModel model;

    public HomeFragmentPresenter(HomeFragmentView view){
        setHomeFragmentView(view);
        setModel();
    }

    private void setModel() {
        this.model = new ProductModel(this);
    }

    private void setHomeFragmentView(HomeFragmentView view) {
    this.view = view;
    }

    public void getProductsList(){
        model.getProductsList();
    }

    @Override
    public void onGetProduct(ProductModel productModel) {
        view.onGetProduct(productModel);
    }
}
