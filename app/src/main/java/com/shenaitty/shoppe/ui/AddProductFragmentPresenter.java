package com.shenaitty.shoppe.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shenaitty.shoppe.R;
import com.shenaitty.shoppe.data.Constants;
import com.shenaitty.shoppe.pojo.ProductModel;

import java.util.Calendar;

public class AddProductFragmentPresenter {

    private AddProductFragmentView view;
    private Activity activity;

    public AddProductFragmentPresenter(AddProductFragmentView view, Activity activity) {
        setView(view);
        setActivity(activity);
    }

    private void setView(AddProductFragmentView view) {
        this.view = view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void addProductToDatabase(ProductModel product, Uri productImageUri) {
        if (productImageUri != null & product != null) {
            String imageName = getUniqueImageName(productImageUri);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference imagesRef = storage.getReference().child(Constants.PRODUCTS_IMAGES);
            imagesRef.child(imageName)
                    .putFile(productImageUri).addOnSuccessListener(taskSnapshot -> {
                product.setImageUrl(Constants.IMG_STORAGE_MAIN_PATH + imageName);
                FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_LINK);
                DatabaseReference productReference = database.getReference(Constants.PRODUCTS);
                productReference.push().setValue(product)
                        .addOnSuccessListener(unused -> view.onAddProductResponse(true))
                        .addOnFailureListener(e -> view.onAddProductResponse(false));
            }).addOnFailureListener(e -> Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show());
        }else{
            Toast.makeText(activity,activity.getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
        }
    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = activity.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private String getUniqueImageName(Uri imageUri) {
        return "prod_" + Calendar.getInstance().getTimeInMillis() + "." + getFileExtension(imageUri);
    }

}
