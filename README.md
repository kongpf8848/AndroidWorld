# AndroidWorld
**Android日常学习总结**

# 仿简书个人主页(CoordinatorLayout+吸顶悬浮+状态栏&Toolbar背景渐变)

* 效果图

![仿简书个人主页](https://github.com/kongpf8848/AndroidWorld/blob/master/screenshots/jianshu_user_info.webp)

* 整体布局(FrameLayout+CoordinatorLayout+AppBarLayout+CollapsingToolbarLayout+TabLayout+ViewPager)

```xml
 <?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/root_view"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    >

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:id="@+id/main_content"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:fitsSystemWindows="true">

        <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/app_bar"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:background="@color/white"
            android:fitsSystemWindows="true"
            >

            <com.google.android.material.appbar.CollapsingToolbarLayout
                android:id="@+id/collapsing_toolbar"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:fitsSystemWindows="true"
              >

                <include layout="@layout/include_user_info" />

                <androidx.appcompat.widget.Toolbar
                    android:layout_width="fill_parent"
                    android:layout_height="?attr/actionBarSize"
                    android:visibility="invisible"
                    app:layout_collapseMode="pin" />


            </com.google.android.material.appbar.CollapsingToolbarLayout>


            <LinearLayout
                android:id="@+id/ll_tablayout"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="-24dp"
                android:orientation="vertical">

                <com.google.android.material.tabs.TabLayout
                    android:id="@+id/tablayout_user"
                    android:layout_width="fill_parent"
                    android:layout_height="48dp"
                    android:background="@color/white"
                    />

            </LinearLayout>

        </com.google.android.material.appbar.AppBarLayout>

        <androidx.viewpager.widget.ViewPager
            android:id="@+id/viewpager_container"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:background="@color/white"
            app:layout_behavior="@string/appbar_scrolling_view_behavior" />
    </androidx.coordinatorlayout.widget.CoordinatorLayout>

    <!--头部布局-->
    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!--状态栏占位-->
        <View
            android:id="@+id/view_status_height"
            android:layout_width="fill_parent"
            android:layout_height="0dip" />

        <FrameLayout
            android:layout_width="fill_parent"
            android:layout_height="?attr/actionBarSize">
            
            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="fill_parent"
                android:layout_height="fill_parent"
                app:contentInsetEnd="0dp"
                app:contentInsetLeft="0dp"
                app:contentInsetRight="0dp"
                app:contentInsetStart="0dp">

            </androidx.appcompat.widget.Toolbar>

        </FrameLayout>

    </LinearLayout>

</FrameLayout>
```
