package com.shenaitty.shoppe.ui;

public class ProductInformationFragmentPresenter {
    private ProductInformationFragmentView view;

    public ProductInformationFragmentPresenter(ProductInformationFragmentView view){
        setView(view);
    }

    public void setView(ProductInformationFragmentView view) {
        this.view = view;
    }
}
