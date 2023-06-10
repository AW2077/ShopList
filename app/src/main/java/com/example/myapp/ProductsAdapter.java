package com.example.myapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> mProducts;
    private ArrayList<Product> listProducts;
    private OnProductClickListener mOnProdListener;
    private DatabaseHelper mDatabase;
    private Cursor mCursor;

    public ProductsAdapter(Context context, ArrayList<Product> listProducts, OnProductClickListener onProductClickListener, Cursor cursor) {
        this.context = context;
        this.listProducts = listProducts;
        this.mProducts = listProducts;
        mOnProdListener = onProductClickListener;
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

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        // Get the data model based on position
        final Product product = listProducts.get(position);

        @SuppressLint("Range") long id = mCursor.getLong(mCursor.getColumnIndex(DatabaseHelper.COUNTER));

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(product.getName());
        ImageView imageView = holder.categoryIcon;
        imageView.setVisibility(View.VISIBLE);

        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

}
