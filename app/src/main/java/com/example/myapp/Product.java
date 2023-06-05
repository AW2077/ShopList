package com.example.myapp;

import java.util.ArrayList;

public class Product {
    public static ArrayList<Product> productArrayList = new ArrayList<>();

    private int id;
    private String mName;
    private int catIcon;
    private int mChecked;
    private String mUnit;
    private String unitNum;

    public Product(int id, String mName, int catIcon, int mChecked, String unitNum, String mUnit) {
        this.id = id;
        this.mName = mName;
        this.catIcon = catIcon;
        this.mChecked = mChecked;
        this.mUnit = mUnit;
        this.unitNum = unitNum;
    }

    public Product(String mName, int catIcon, int mChecked, String unitNum, String mUnit) {
        this.mName = mName;
        this.catIcon = catIcon;
        this.mChecked = mChecked;
        this.mUnit = mUnit;
        this.unitNum = unitNum;
    }

    public Product(String mName, String unitNum, String  mUnit) {
        this.mName = mName;
        this.mUnit = mUnit;
        this.unitNum = unitNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(int catIcon) {
        this.catIcon = catIcon;
    }

    public int isChecked() {
        return mChecked;
    }

    public void setChecked(int mChecked) {
        this.mChecked = mChecked;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }


}