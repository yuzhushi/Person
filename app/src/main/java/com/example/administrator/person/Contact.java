package com.example.administrator.person;

import java.io.Serializable;

/**
 * Created by DELL on 2017/11/11.
 */

public class Contact implements Serializable {
    private String mName;
    private int mType;

    public Contact(String name, int type) {
        mName = name;
        mType = type;
    }

    public String getmName() {
        return mName;
    }

    public int getmType() {
        return mType;
    }

}
