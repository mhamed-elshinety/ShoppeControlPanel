package com.shenaitty.shoppe.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

public class EditProductFragment extends Fragment {

    private ImageView productIV;
    private EditText productNameET, productPriceET,productCategoryET, moreInfoET,productDescriptionET,
                     discountET,widthET,lengthET,heightET,productQuantityET;
    private TextView editProductTv;
    private NavController navController;
    private ProductModel product;


    public EditProductFragment() {

    }
    public static EditProductFragment newInstance() {
        EditProductFragment fragment = new EditProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineFields();
        defineViews(view);
        initiateNavController(view);
        putDataInViews();
    }

    private void putDataInViews() {
        if(product!=null){
            productNameET.setText(product.getName());
            productPriceET.setText(String.format(Double.toString(product.getPrice())));
            productCategoryET.setText(product.getCategory());
            moreInfoET.setText(product.getMoreInfo());
            productDescriptionET.setText(product.getDescription());
            discountET.setText(String.format(Double.toString(product.getDiscount())));
            widthET.setText(String.format(Double.toString(product.getWidthInCM())));
            heightET.setText(String.format(Double.toString(product.getHeightInCM())));
            lengthET.setText(String.format(Double.toString(product.getLengthInCM())));
            productQuantityET.setText(String.format(Integer.toString(product.getQuantity())));
        }
        editProductTv.setText(getResources().getString(R.string.edit_product));
    }

    private void defineViews(View view){
        productIV = view.findViewById(R.id.product_iv);
        productNameET = view.findViewById(R.id.product_name_et);
        productPriceET = view.findViewById(R.id.product_price_et);
        productCategoryET = view.findViewById(R.id.product_category_et);
        moreInfoET = view.findViewById(R.id.product_more_info_et);
        productDescriptionET =view.findViewById(R.id.product_description_et);
        discountET = view.findViewById(R.id.product_discount_et);
        widthET = view.findViewById(R.id.product_width_et);
        lengthET = view.findViewById(R.id.product_length_et);
        heightET =view.findViewById(R.id.product_height_et);
        productQuantityET = view.findViewById(R.id.product_quantity_et);
        editProductTv = view.findViewById(R.id.add_product_tv);
    }

    private void initiateNavController(View view){
        navController = Navigation.findNavController(view);
    }

    private void defineFields(){
        try {
            product = (ProductModel) getArguments().getSerializable(Constants.PRODUCT);
        }catch (Exception e){

        }
    }


}