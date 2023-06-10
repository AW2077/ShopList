package com.example.myapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapp.Product;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ProductDB";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME = "Product";
    public static final String COUNTER = "Counter";

    private static final String TITLE_FIELD = "name";
    private static final String ICON_FIELD = "icon";
    private static final String CHECK_FIELD = "checked";
    private static final String UNIT_FIELD = "unit";
    private static final String NUM_FIELD = "num";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COUNTER + " INTEGER PRIMARY KEY, " +
            TITLE_FIELD + " TEXT, " +
            ICON_FIELD + " INT, " +
            CHECK_FIELD + " INT, " +
            UNIT_FIELD + " TEXT, " +
            NUM_FIELD + " TEXT) ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addProductToDatabase(Product product) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE_FIELD, product.getName());
        contentValues.put(ICON_FIELD, product.getCatIcon());
        contentValues.put(CHECK_FIELD, product.isChecked());
        contentValues.put(UNIT_FIELD, product.getUnit());
        contentValues.put(NUM_FIELD, product.getUnitNum());


        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteProductInDatabase(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COUNTER + " = " + id, null);
    }

    public int updateProductInDatabase(long id, Product product) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE_FIELD, product.getName());
        contentValues.put(ICON_FIELD, product.getCatIcon());
        contentValues.put(CHECK_FIELD, product.isChecked());
        contentValues.put(UNIT_FIELD, product.getUnit());
        contentValues.put(NUM_FIELD, product.getUnitNum());


        return sqLiteDatabase.update(TABLE_NAME, contentValues, COUNTER + " = " +  id, null);
    }

/*    public void populateProductListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    int icon = result.getInt(3);
                    String unit = result.getString(4);
                    String num = result.getString(5);
                    int checked = result.getInt(6);
                    Product product = new Product(id, title, icon, checked, unit, num);
                    Product.productArrayList.add(product);

                }
            }
        }
    }*/

    public ArrayList<Product> listProducts() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Product> storedProducts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(1);
                int icon = cursor.getInt(2);
                int checked = cursor.getInt(3);
                String unit = cursor.getString(4);
                String num = cursor.getString(5);
                Product product = new Product(title, icon, checked, unit, num);
                storedProducts.add(product);


            }

        }
        cursor.close();
        return storedProducts;
    }
}