package com.shenaitty.shoppe.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

public class ProductInformationFragment extends Fragment implements ProductInformationFragmentView {

    private ImageView productIV, shareIV;
    private TextView productNameTV, productPriceTV,editProductTV,productInfoTV, descriptionTV, additionalInfoTV;
    private LinearLayout viewMoreLayout;
    private ProductModel product;


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
        settingDataInViews();
    }

    private void defineViews(View view){
        productIV = view.findViewById(R.id.product_iv);
        shareIV = view.findViewById(R.id.share_btn);
        productNameTV = view.findViewById(R.id.product_name_tv);
        productPriceTV = view.findViewById(R.id.product_price_tv);
        editProductTV = view.findViewById(R.id.edit_tv);
        productInfoTV = view.findViewById(R.id.product_information_tv);
        descriptionTV = view.findViewById(R.id.description_tv);
        viewMoreLayout = view.findViewById(R.id.view_more_linear_layout);
        additionalInfoTV = view.findViewById(R.id.additional_info_tv);
    }
    private void defineFields() {
        product = (ProductModel) getArguments().getSerializable(Constants.PRODUCT);
    }
    private void settingDataInViews() {
        if(product!=null){
            productNameTV.setText(product.getName());
            productInfoTV.setText(product.getMoreInfo());
            descriptionTV.setText(product.getDescription());
            productPriceTV.setText(""+product.getPrice());
        }

    }



}