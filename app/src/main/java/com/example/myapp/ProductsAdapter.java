package com.example.myapp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.database.DatabaseHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> mProducts;
    private ArrayList<Product> listProducts;
    private OnProductClickListener mOnProdListener;
    private DatabaseHelper mDatabase;
    private Cursor mCursor;

    public ProductsAdapter(Context context, ArrayList<Product> listProducts, Cursor cursor) {
        this.context = context;
        this.listProducts = listProducts;
        this.mProducts = listProducts;
        mDatabase = new DatabaseHelper(context);
        mCursor = cursor;

    }

    public interface OnProductClickListener {
        void onProductClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public ImageView categoryIcon;
        public OnProductClickListener onProductClickListener;

        public ViewHolder(View itemView, OnProductClickListener onProductClickListener) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.product_name);
            categoryIcon = (ImageView) itemView.findViewById(R.id.category_icon);
            this.onProductClickListener = onProductClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onProductClickListener.onProductClick(getAdapterPosition());

        }
    }



    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(contactView, mOnProdListener);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        final Product product = listProducts.get(position);
        @SuppressLint("Range") long id = mCursor.getLong(mCursor.getColumnIndex(DatabaseHelper.COUNTER));

        holder.nameTextView.setText(product.getName());

        ImageView imageView = holder.categoryIcon;
        imageView.setVisibility(View.VISIBLE);

        holder.itemView.setTag(id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProductBottomSheet(id, product);
            }
        });
    }

    private void editProductBottomSheet(long id, final Product product) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subview = inflater.inflate(R.layout.fr_bottom_sheet, null);

        Button btnUpdate = subview.findViewById(R.id.btnUpdate);
        final EditText etName = subview.findViewById(R.id.etEditProductName);
        final EditText etNum = subview.findViewById(R.id.etEditNum);
        final EditText etUnit = subview.findViewById(R.id.etEditUnit);

        if (product != null){
            etName.setText(product.getName());
            etNum.setText(String.valueOf(product.getUnitNum()));
            etUnit.setText(product.getUnit());
        }

        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(subview);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String num = etNum.getText().toString();
                final String unit = etUnit.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                } else {
                    mDatabase.updateProductInDatabase(id, new Product(name, unit, num));
                    ((Activity) context).finish();
                    context.startActivity(((Activity)
                            context).getIntent());
                       // TODO: przenieść gdzieś gdzie adapter notify działa
                }

            }
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();

    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

}
