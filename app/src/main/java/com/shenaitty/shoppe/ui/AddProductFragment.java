package com.shenaitty.shoppe.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

public class AddProductFragment extends Fragment implements View.OnClickListener, AddProductFragmentView {

    private EditText nameEt, priceEt, descriptionEt, categoryEt, moreInfoEt,
                     discountEt, widthEt, heightEt, lengthEt, quantityEt;
    private TextView addProductTv;
    private ImageView productIv;
    private ProgressBar progressBar;

    private AddProductFragmentPresenter presenter;
    private ActivityResultLauncher<String> launcher;
    private Uri productImageUri;

    public AddProductFragment newInstance() {
        return new AddProductFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityResultLauncher();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews(view);
        defineFields();
        setClickActions();
        setSavedInstanceStates(savedInstanceState);
        setSavedImage();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(productImageUri !=null)
            outState.putParcelable(Constants.IMAGE_URI, productImageUri);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_product_tv) {
            if (isCompletedData()) {
                presenter.addProductToDatabase(getProductFromViews(), productImageUri);
                progressBar.setVisibility(View.VISIBLE);
            }
            else
                Toast.makeText(getContext(), getString(R.string.complete_your_data), Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.product_iv)
            selectImageFromGallery();
    }

    @Override
    public void onAddProductResponse(boolean isSuccessful) {
        if (isSuccessful) {
            Toast.makeText(getContext(), getString(R.string.product_added), Toast.LENGTH_LONG).show();
            resetFields();
            hideSoftKeyboard(requireView());
        } else {
            Toast.makeText(getContext(), getString(R.string.error_msg_1), Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.GONE);
    }

    private void setClickActions() {
        addProductTv.setOnClickListener(this);
        productIv.setOnClickListener(this);
    }

    private void defineViews(View view) {
        nameEt = view.findViewById(R.id.product_name_et);
        priceEt = view.findViewById(R.id.product_price_et);
        descriptionEt = view.findViewById(R.id.product_description_et);
        categoryEt = view.findViewById(R.id.product_category_et);
        moreInfoEt = view.findViewById(R.id.product_more_info_et);
        discountEt = view.findViewById(R.id.product_discount_et);
        widthEt = view.findViewById(R.id.product_width_et);
        lengthEt = view.findViewById(R.id.product_length_et);
        heightEt = view.findViewById(R.id.product_height_et);
        quantityEt = view.findViewById(R.id.product_quantity_et);
        addProductTv = view.findViewById(R.id.add_product_tv);
        productIv = view.findViewById(R.id.product_iv);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    private void defineFields() {
        presenter = new AddProductFragmentPresenter(this, getActivity());
    }

    private boolean isCompletedData() {
        return nameEt.getText().length() >= 1 && priceEt.getText().length() >= 1 && descriptionEt.getText().length() >= 1 && categoryEt.getText().length() >= 1
                && moreInfoEt.getText().length() >= 1 && discountEt.getText().length() >= 1 && widthEt.getText().length() >= 1 && lengthEt.getText().length() >= 1
                && heightEt.getText().length() >= 1 && quantityEt.getText().length() >= 1 && productImageUri!=null;
    }

    private ProductModel getProductFromViews() {
        String name = nameEt.getText().toString();
        float price = Float.parseFloat(priceEt.getText().toString());
        String desc = descriptionEt.getText().toString();
        String category = categoryEt.getText().toString();
        String moreInfo = moreInfoEt.getText().toString();
        float discount = Float.parseFloat(discountEt.getText().toString());
        float width = Float.parseFloat(widthEt.getText().toString());
        float height = Float.parseFloat(heightEt.getText().toString());
        float length = Float.parseFloat(lengthEt.getText().toString());
        int quantity = Integer.parseInt(quantityEt.getText().toString());
        return new ProductModel(Constants.NA, name, desc, category, moreInfo, Constants.NA, price, discount, width, length, height, quantity,null);
    }

    private void resetFields() {
        nameEt.setText("");
        priceEt.setText("");
        descriptionEt.setText("");
        categoryEt.setText("");
        moreInfoEt.setText("");
        discountEt.setText("");
        widthEt.setText("");
        lengthEt.setText("");
        heightEt.setText("");
        quantityEt.setText("");
        productIv.setImageResource(R.drawable.add_product);
        productImageUri=null;
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //لازم علشان استخدم ال launcher يكون ال initializing بتاعة قبل ال fragment creation
    private void setActivityResultLauncher() {
        this.launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                productIv.setImageURI(uri);
                setImageUri(uri);
            }
            else
                Toast.makeText(getContext(), getString(R.string.no_image_selected), Toast.LENGTH_SHORT).show();
        });
    }

    private void setImageUri(Uri uri) {
        this.productImageUri = uri;
    }


    private void selectImageFromGallery() {
        try {
            launcher.launch("image/*"); //string is the input type
        } catch (ActivityNotFoundException e) {
            Log.e(Constants.LOG_TAG + getClass().getName(), "selectImageFromGallery: " + e);
        }
    }

    private void setSavedInstanceStates(Bundle savedInstanceState) {
        if(savedInstanceState!=null)
            productImageUri = savedInstanceState.getParcelable(Constants.IMAGE_URI);
    }

    private void setSavedImage() {
        if(productImageUri !=null)
            productIv.setImageURI(productImageUri);
    }
}
