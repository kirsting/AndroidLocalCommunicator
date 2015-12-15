package com.binasphere.androidlocalcommunicator.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kerstin on 2015/12/15.
 */
public class TypeMoon implements Parcelable {
    public int id;
    public String title;
    public String name;
    public String imageUrl;

    protected TypeMoon(Parcel in) {
        id = in.readInt();
        title = in.readString();
        name = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TypeMoon> CREATOR = new Creator<TypeMoon>() {
        @Override
        public TypeMoon createFromParcel(Parcel in) {
            return new TypeMoon(in);
        }

        @Override
        public TypeMoon[] newArray(int size) {
            return new TypeMoon[size];
        }
    };
}
