package com.example.myapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Product;
import com.example.myapp.ProductsAdapter;
import com.example.myapp.R;
import com.example.myapp.database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FragmentZakupy extends Fragment {
    private DatabaseHelper mDatabase;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_zakupy, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mDatabase = new DatabaseHelper(this.getContext());
        ArrayList<Product> allProducts = mDatabase.listProducts();
        RecyclerView rvProducts = view.findViewById(R.id.rvProducts);
        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.addItemDecoration(new DividerItemDecoration(requireContext(),layoutManager.getOrientation()));

        ProductsAdapter adapter = new ProductsAdapter(getContext(), allProducts, getAllItems());
        rvProducts.setAdapter(adapter);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product newproduct = new Product("nowy produkt", R.drawable.ic_recipe, 1, null, "0");
                allProducts.add(0, newproduct);
                adapter.notifyItemInserted(0);
                rvProducts.smoothScrollToPosition(0);

                mDatabase.addProductToDatabase(newproduct);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction){
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                Product deletedProduct = allProducts.get(viewHolder.getAdapterPosition());

                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                mDatabase.deleteProductInDatabase((Long) viewHolder.itemView.getTag());
                allProducts.remove(viewHolder.getAdapterPosition());
                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());


                // below line is to display our snackbar with action.
                Snackbar.make(rvProducts, "Usunięto " + deletedProduct.getName(), Snackbar.LENGTH_SHORT).setAction("Cofnij", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        allProducts.add(position, deletedProduct);

                        // below line is to notify item is
                        // added to our adapter class.
                        adapter.notifyItemInserted(position);
                        mDatabase.addProductToDatabase(deletedProduct);
                    }
                }).show();
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(rvProducts);


        }

    private Cursor getAllItems() {
        return mDatabase.getReadableDatabase().query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
    }

    /*@Override
    public void onProductClick(int position) {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        View view = getLayoutInflater().inflate(R.layout.fr_bottom_sheet, null);

        EditText etName = dialog.findViewById(R.id.etEditProductName);
        EditText etNum = dialog.findViewById(R.id.etEditNum);
        EditText etUnit = dialog.findViewById(R.id.etEditUnit);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

*//*        etName.setText(products.get(position).getName());
        etNum.setText(products.get(position).getUnitNum());
        etUnit.setText(products.get(position).getUnit());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "", num = "", unit = "";
                name = etName.getText().toString();
                num = etNum.getText().toString();
                unit = etUnit.getText().toString();

                products.set(position, new Product(name, num, unit));

            }
        });*//*

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        dialog.show();

    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
}