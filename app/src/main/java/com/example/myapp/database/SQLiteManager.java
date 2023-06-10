package com.example.myapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp.Product;

import java.lang.annotation.Target;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "ProductDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Product";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "name";
    private static final String ICON_FIELD = "icon";
    private static final String CHECK_FIELD = "checked";
    private static final String UNIT_FIELD = "unit";
    private static final String NUM_FIELD = "num";


    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE " +  TABLE_NAME + "(" +
                COUNTER + " INTEGER PRIMARY KEY, " +
                ID_FIELD + " INT, " +
                TITLE_FIELD + " TEXT, " +
                ICON_FIELD + " INT, " +
                CHECK_FIELD + " INT, " +
                UNIT_FIELD + " TEXT, " +
                NUM_FIELD + " TEXT) ";

        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProductToDatabase(Product product){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, product.getId());
        contentValues.put(TITLE_FIELD, product.getName());
        contentValues.put(ICON_FIELD, product.getCatIcon());
        contentValues.put(UNIT_FIELD, product.getUnit());
        contentValues.put(NUM_FIELD, product.getUnitNum());
        contentValues.put(CHECK_FIELD, product.isChecked());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateProductListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (result.getCount() != 0){
                while (result.moveToNext()){
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    int icon = result.getInt(3);
                    String unit = result.getString(4);
                    String num = result.getString(5);
                    int  checked = result.getInt(6);
                    Product product = new Product(id, title, icon, checked, unit, num);
                    Product.productArrayList.add(product);

                }
            }
        }
    }

    public void updateProductInDatabase(Product product){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, product.getId());
        contentValues.put(TITLE_FIELD, product.getName());
        contentValues.put(ICON_FIELD, product.getCatIcon());
        contentValues.put(UNIT_FIELD, product.getUnit());
        contentValues.put(NUM_FIELD, product.getUnitNum());
        contentValues.put(CHECK_FIELD, product.isChecked());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =?", new String[]{String.valueOf(product.getId())});
    }
}
