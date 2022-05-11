package com.shenaitty.shoppe.adapters;

import android.content.Context;
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
import com.shenaitty.shoppe.data.Assistant;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.ArrayList;

public class ProductsRecAdapter extends RecyclerView.Adapter<ProductsRecAdapter.ViewHolder> {

    private ArrayList<ProductModel> products;
    private Context context;
    public ProductsRecAdapter(ArrayList<ProductModel> products){
        setProducts(products);
    }


    @NonNull
    @Override
    public ProductsRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        setContext(parent.getContext());
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_horizontal,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsRecAdapter.ViewHolder holder, int position) {
        fillViewsWithData(holder,products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void fillViewsWithData(ViewHolder holder, ProductModel currProduct) {
        setProductName(holder,currProduct.getName());
        setMiniInfo(holder,currProduct.getMoreInfo());
        setProductPriceBeforeDiscount(holder, currProduct.getPrice(),currProduct.getDiscount());
        setProductPrice(holder, currProduct.getPrice(),currProduct.getDiscount());
        setQuantity(holder,currProduct.getQuantity());
        setLabel(holder,currProduct.getDiscount(),currProduct.getQuantity(),currProduct.getCategory());

    }

    private void setProducts(ArrayList<ProductModel> products) {
        this.products = products;
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
            holder.priceBeforeDiscountTv.setVisibility(View.VISIBLE);
            holder.priceBeforeDiscountTv.setText(Assistant.formatDecimal(2,price));
            holder.priceBeforeDiscountTv.setPaintFlags(holder.priceBeforeDiscountTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
            holder.priceBeforeDiscountTv.setVisibility(View.GONE);
    }

    private void setProductPrice(ViewHolder holder, float price, float discount){
        if(discount>0)
            holder.productPriceTv.setText(Assistant.formatDecimal(2,price - (price * (discount / 100))));
        else
            holder.productPriceTv.setText(Assistant.formatDecimal(2,price));
    }

    private void setQuantity(ViewHolder holder, int quantity){
        if(quantity>0)
            holder.quantityTv.setText(String.valueOf(quantity));
        else
            holder.quantityTv.setText(Constants.NA);
    }

    private void setLabel(ViewHolder holder, double discount, int quantity, String category){
        if(quantity<1) {
            holder.labelTv.setText(context.getString(R.string.sold));
            holder.labelTv.setBackground(context.getDrawable(R.drawable.drawable_accent_bg_with_accent_boarders));
            holder.labelTv.setTextColor(context.getResources().getColor(R.color.white));
        }
        else if(discount>0) {
            holder.labelTv.setText("- %" + discount);
            holder.labelTv.setBackground(context.getDrawable(R.drawable.drawable_dark_bg_with_dark_borders));
            holder.labelTv.setTextColor(context.getResources().getColor(R.color.white));
        }
        else {
            holder.labelTv.setText(category);
            holder.labelTv.setBackground(context.getResources().getDrawable(R.drawable.drawable_light_bg_with_light_boarders));
            holder.labelTv.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView productNameTv, priceBeforeDiscountTv, productPriceTv, miniInfoTv, quantityTv, labelTv;
        private LinearLayout seeMoreLinearLayout;
        private ImageView editIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            defineViews(itemView);
        }

        private void defineViews(View view){
            productNameTv = view.findViewById(R.id.product_name_et);
            priceBeforeDiscountTv = view.findViewById(R.id.product_price_before_discount_tv);
            productPriceTv = view.findViewById(R.id.product_price_et);
            miniInfoTv = view.findViewById(R.id.product_mini_description_tv);
            quantityTv = view.findViewById(R.id.quantity_tv);
            labelTv = view.findViewById(R.id.label_tv);
            seeMoreLinearLayout = view.findViewById(R.id.see_more_linear_layout);
            editIv = view.findViewById(R.id.edit_iv);
        }
    }
}
