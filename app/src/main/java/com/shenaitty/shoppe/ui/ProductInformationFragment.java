package com.shenaitty.shoppe.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Assistant;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

public class ProductInformationFragment extends Fragment implements ProductInformationFragmentView, View.OnClickListener {

    private ImageView productIV;
    private TextView productNameTV, productPriceTV,editProductTV,productInfoTV, descriptionTV,
            additionalInfoTV, priceBeforeDiscountTV;
    private LinearLayout viewMoreLayout;
    private ProductModel product;
    boolean isViewMoreExpanded = false;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews(view);
        defineFields();
        initiateNavController(view);
        setSavedStates(savedInstanceState);
        settingDataInViews();
        settingListeners();
    }

    private void setSavedStates(Bundle savedInstanceState) {
        if(savedInstanceState!=null) {
            isViewMoreExpanded = savedInstanceState.getBoolean(Constants.VU_MORE);
            productInfoTV.setMaxLines(100);
        }
    }

    private void settingListeners() {
        viewMoreLayout.setOnClickListener(this);
        editProductTV.setOnClickListener(this);
    }

    private void defineViews(View view){
        productIV = view.findViewById(R.id.product_iv);
        productNameTV = view.findViewById(R.id.product_name_tv);
        productPriceTV = view.findViewById(R.id.product_price_tv);
        editProductTV = view.findViewById(R.id.edit_tv);
        productInfoTV = view.findViewById(R.id.product_information_tv);
        descriptionTV = view.findViewById(R.id.description_tv);
        viewMoreLayout = view.findViewById(R.id.view_more_linear_layout);
        additionalInfoTV = view.findViewById(R.id.additional_info_tv);
        priceBeforeDiscountTV = view.findViewById(R.id.product_price_before_discount_tv);
    }
    private void defineFields() {
        product = (ProductModel) getArguments().getSerializable(Constants.PRODUCT);
    }
    private void initiateNavController(View view) {
        navController = Navigation.findNavController(view);
    }
    private void settingDataInViews() {
        if(product!=null){
            productNameTV.setText(product.getName());
            productInfoTV.setText(product.getMoreInfo());
            descriptionTV.setText(product.getDescription());
            setPriceBeforeDiscount(product.getPrice(),product.getDiscount());
            setPrice(product.getPrice(),product.getDiscount());
        }
    }

    private void setPriceBeforeDiscount(double price, double discount) {
        if(discount>0){
            priceBeforeDiscountTV.setText(Assistant.formatDecimal(2,price));
            priceBeforeDiscountTV.setPaintFlags(priceBeforeDiscountTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else
            priceBeforeDiscountTV.setVisibility(View.GONE);
    }

    private void setPrice(double price, double discount){
        productPriceTV.setText(Assistant.formatDecimal(2,price-price*(discount/100)));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.view_more_linear_layout:
                setViewMoreState();
                break;
            case R.id.edit_tv:
                navigateToEditProductFragment();
                break;
        }

    }

    private void navigateToEditProductFragment() {
        Bundle productBundle = new Bundle();
        productBundle.putSerializable(Constants.PRODUCT,product);
        navController.navigate(R.id.action_productInformationFragment_to_editProductFragment,productBundle);
    }

    private void setViewMoreState() {
        if(!isViewMoreExpanded) {
            productInfoTV.setMaxLines(100);
            isViewMoreExpanded=true;
        }
        else {
            productInfoTV.setMaxLines(2);
            isViewMoreExpanded=false;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.VU_MORE,isViewMoreExpanded);
        Log.d(Constants.LOG_TAG, "onSaveInstanceState:" + isViewMoreExpanded);
    }

}