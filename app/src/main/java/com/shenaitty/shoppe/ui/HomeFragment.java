package com.shenaitty.shoppe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.adapters.ProductsRecAdapter;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.listeners.OnEditClickListener;
import com.shenaitty.shoppe.listeners.OnSeeMoreClickListener;
import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeFragmentView, OnSeeMoreClickListener, OnEditClickListener {

    //TODO: Caching data

    private ExtendedFloatingActionButton manageBtn;
    private FloatingActionButton addProductFloatingBtn, addOfferFloatingBtn;
    private TextView addProductTv, addOfferTv;
    private RecyclerView productRecView;

    private ProductsRecAdapter adapter;
    private ArrayList<ProductModel> products;
    private boolean isShrink = true;
    private HomeFragmentPresenter presenter;
    private NavController navController;




    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews(view);
        defineFields();
        initializeFloatingButtons();
        setSavedInstanceStates(savedInstanceState);
        setButtonsSavedState();
        initializePresenter();
        initializeNavController(view);
        initializeProductRecView();
        setClickActions();
        presenter.getProductsList();
    }

    private void defineFields() {
        products = new ArrayList<>();
        adapter = new ProductsRecAdapter(this.products);
    }

    private void initializeNavController(View view) {
        navController = Navigation.findNavController(view);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.KEY_IS_SHRINK,isShrink);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.extended_float_action_btn:
                changeButtonsState();
                break;
            case R.id.add_product_fab:
                    navigateToAddProductFragment();
                break;
        }
    }

    private void navigateToAddProductFragment() {
        navController.navigate(R.id.action_homeFragment_to_addProductFragment);
    }

    private void navigateToProductInformationFragment(ProductModel product){
        Bundle productBnd = new Bundle();
        productBnd.putSerializable(Constants.PRODUCT, product);
        navController.navigate(R.id.action_homeFragment_to_productInformationFragment, productBnd);
    }

    @Override
    public void onGetProduct(ProductModel product) {
        products.add(product);
        adapter.notifyItemInserted(products.size()-1);
        adapter.notifyDataSetChanged();
    }

    private void initializeProductRecView() {
        productRecView.setAdapter(adapter);
        productRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter.setOnSeeMoreClickListener(this);
        adapter.setOnEditClickListener(this);
    }

    private void setProducts(ArrayList<ProductModel> products) {
        // TODO: I must put some restrictions for products
        this.products = products;
    }

    private void defineViews(View view) {
        manageBtn = view.findViewById(R.id.extended_float_action_btn);
        addOfferFloatingBtn = view.findViewById(R.id.add_offer_fab);
        addProductFloatingBtn = view.findViewById(R.id.add_product_fab);
        addOfferTv = view.findViewById(R.id.add_offer_tv);
        addProductTv = view.findViewById(R.id.add_product_tv);
        productRecView = view.findViewById(R.id.products_rec_view);
    }

    private void initializeFloatingButtons() {
        manageBtn.shrink();
        hideFloatingActionButtons();
    }

    private void setSavedInstanceStates(Bundle savedInstanceState) {
        if(savedInstanceState!=null)
            isShrink = savedInstanceState.getBoolean(Constants.KEY_IS_SHRINK);
    }

    private void setButtonsSavedState() {
        if(isShrink) {
            manageBtn.shrink();
            hideFloatingActionButtons();
        }
        else {
            manageBtn.extend();
            showFloatingActionButtons();
        }
    }

    private void setClickActions(){
        manageBtn.setOnClickListener(this);
        addProductFloatingBtn.setOnClickListener(this);
    }

    private void changeButtonsState() {
        if(isShrink) {
            manageBtn.extend();
            showFloatingActionButtons();
            isShrink = false;
        }
        else {
            manageBtn.shrink();
            hideFloatingActionButtons();
            isShrink=true;
        }
    }

   private void hideFloatingActionButtons(){
       addProductFloatingBtn.hide();
       addOfferFloatingBtn.hide();
       addOfferTv.setVisibility(View.GONE);
       addProductTv.setVisibility(View.GONE);
   }

   private void showFloatingActionButtons(){
       addProductFloatingBtn.show();
       addOfferFloatingBtn.show();
       addOfferTv.setVisibility(View.VISIBLE);
       addProductTv.setVisibility(View.VISIBLE);
   }

    private void initializePresenter() {
        this.presenter = new HomeFragmentPresenter(this);
    }

    @Override
    public void onSeeMoreClickListener(int position) {
        ProductModel product =  products.get(position);
        navigateToProductInformationFragment(product);
    }

    @Override
    public void OnEditClick(int position) {

    }
}