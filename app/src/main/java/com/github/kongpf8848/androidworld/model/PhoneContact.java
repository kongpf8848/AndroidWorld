package com.github.kongpf8848.androidworld.model;

import android.os.Looper;

public class PhoneContact {


    private String name;
    private String phone;

    private String avatar;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }


    public PhoneContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
        if (phone.startsWith("15") || phone.startsWith("135")) {
            this.avatar = "http://image.huajiao.com/943063fe44aa1bcc4ece46ccbf358af8.jpg";
        } else {
            this.avatar = "http://gips3.baidu.com/it/u=764883555,2569275522&fm=3028&app=3028&f=JPEG&fmt=auto?w=960&h=1280";
        }
    }
}
