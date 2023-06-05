package com.example.myapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> mProducts;
    private OnProductClickListener mOnProdListener;


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

    public ProductsAdapter(List<Product> products, OnProductClickListener onProductClickListener) {
        mProducts = products;
        mOnProdListener = onProductClickListener;
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
        // Get the data model based on position
        Product product = mProducts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(product.getName());
        ImageView imageView = holder.categoryIcon;
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

}
