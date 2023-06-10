package com.example.myapp;

public class Product {

    private String mName;
    private int catIcon;
    private int mChecked;
    private String mUnit;
    private String unitNum;

    public Product(String mName, int catIcon, int mChecked, String mUnit, String unitNum) {
        this.mName = mName;
        this.catIcon = catIcon;
        this.mChecked = mChecked;
        this.mUnit = mUnit;
        this.unitNum = unitNum;
    }

    public Product(String mName, String  mUnit, String unitNum) {
        this.mName = mName;
        this.mUnit = mUnit;
        this.unitNum = unitNum;
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