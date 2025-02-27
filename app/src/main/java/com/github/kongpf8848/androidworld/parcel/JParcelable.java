package com.github.kongpf8848.androidworld.parcel;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class JParcelable implements Parcelable {
    private int id;
    private String name;

    public JParcelable(int id, String name) {
        this.id = id;
        this.name = name;

    }

    private JParcelable(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<JParcelable> CREATOR
            = new Parcelable.Creator<JParcelable>() {
        @Override
        public JParcelable createFromParcel(Parcel in) {
            return new JParcelable(in);
        }

        @Override
        public JParcelable[] newArray(int size) {
            return new JParcelable[size];
        }
    };
}