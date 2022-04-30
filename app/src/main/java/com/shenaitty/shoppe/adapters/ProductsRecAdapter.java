package com.shenaitty.shoppe.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.ArrayList;

public class ProductsRecAdapter extends RecyclerView.Adapter<ProductsRecAdapter.ViewHolder> {

    private ArrayList<ProductModel> products;

    public ProductsRecAdapter(ArrayList<ProductModel> products){
        setProducts(products);
    }

    private void setProducts(ArrayList<ProductModel> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_horizontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsRecAdapter.ViewHolder holder, int position) {
        fillViewsWithData(holder,products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private void fillViewsWithData(ViewHolder holder, ProductModel currProduct) {
        setProductName(holder,currProduct.getName());
        setMiniInfo(holder,currProduct.getMoreInfo());
        setProductPriceBeforeDiscount(holder, currProduct.getPrice(),currProduct.getDiscount());
        setProductPrice(holder, currProduct.getPrice(),currProduct.getDiscount());
        setQuantity(holder,currProduct.getQuantity());

    }

    private void setProductName(ViewHolder holder, String productName){
        if(productName!=null)
            holder.productNameTv.setText(productName);
        else
            holder.productNameTv.setText(Constants.NA);
    }

    private void setMiniInfo(ViewHolder holder, String miniInfo){
        if(miniInfo!=null)
            holder.miniInfoTv.setText(miniInfo);
        else
            holder.miniInfoTv.setText(Constants.NA);
    }

    private void setProductPriceBeforeDiscount(ViewHolder holder, float price, float discount){
        if(discount>0) {
            holder.priceBeforeDiscountTv.setText(String.valueOf(price));
            holder.priceBeforeDiscountTv.setPaintFlags(holder.priceBeforeDiscountTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
            holder.priceBeforeDiscountTv.setVisibility(View.GONE);
    }

    private void setProductPrice(ViewHolder holder, float price, float discount){
        if(discount>0)
            holder.productPriceTv.setText(String.valueOf(Math.round(price-(price*(discount/100)))/100.0));
        else
            holder.productPriceTv.setText(String.valueOf(price));
    }

    private void setQuantity(ViewHolder holder, int quantity){
        if(quantity>0)
            holder.quantityTv.setText(String.valueOf(quantity));
        else
            holder.quantityTv.setText(Constants.NA);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView productNameTv, priceBeforeDiscountTv, productPriceTv, miniInfoTv, quantityTv;
        private LinearLayout seeMoreLinearLayout;
        private ImageView editIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            defineViews(itemView);
        }

        private void defineViews(View view){
            productNameTv = view.findViewById(R.id.product_name_tv);
            priceBeforeDiscountTv = view.findViewById(R.id.product_price_before_discount_tv);
            productPriceTv = view.findViewById(R.id.product_price_tv);
            miniInfoTv = view.findViewById(R.id.product_mini_description_tv);
            quantityTv = view.findViewById(R.id.quantity_tv);
            seeMoreLinearLayout = view.findViewById(R.id.see_more_linear_layout);
            editIv = view.findViewById(R.id.edit_iv);
        }
    }
}
