package com.github.kongpf8848.androidworld.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityContentProviderBinding;
import com.github.kongpf8848.androidworld.model.PhoneContact;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends BaseActivity<ActivityContentProviderBinding> {

    private static final String TAG = "ContentProviderActivity";
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    private ContactsObserver contactsObserver;
    private RecyclerView.Adapter adapter;

    private class ContactsObserver extends ContentObserver {
        public ContactsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            this.onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            // 当通讯录数据变化时触发
            Log.d(TAG, "Contacts data changed! URI: " + uri);
            loadContacts();
        }
    }

    private List<PhoneContact> dataList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_content_provider;
    }

    @Override
    protected void onCreateEnd(@Nullable Bundle savedInstanceState) {
        super.onCreateEnd(savedInstanceState);
        contactsObserver = new ContactsObserver(new Handler());
        initRecyclerView();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            loadContacts();
            registerContactsObserver();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(contactsObserver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                registerContactsObserver();
            }
        }
    }

    private void registerContactsObserver() {
        ContentResolver contentResolver = getContentResolver();
        contentResolver.registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, contactsObserver);
    }

    private void loadContacts() {

        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null
        );
        if (cursor != null) {
            // 处理联系人数据
            Log.d(TAG, "loadContacts: " + cursor.getColumnCount());
            dataList.clear();
            while (cursor.moveToNext()) {
                int c0 = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                int c1 = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                int c2 = cursor.getColumnIndex(ContactsContract.PhoneLookup.HAS_PHONE_NUMBER);
                String contactId = cursor.getString(c0);
                String name = cursor.getString(c1);
                int hasPhone = cursor.getInt(c2);
                String phoneNumber = "";
                if (hasPhone > 0) {
                    phoneNumber = getPhoneNumbers(contactId);
                }
                dataList.add(new PhoneContact(name, phoneNumber));
            }
            adapter.notifyDataSetChanged();
            cursor.close();
        }
    }

    @SuppressLint("Range")
    private String getPhoneNumbers(String contactId) {
        String number = "";
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] phoneProjection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };

        String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
        String[] selectionArgs = new String[]{contactId};

        // 执行查询
        Cursor phoneCursor = getContentResolver().query(
                phoneUri,
                phoneProjection,
                selection,
                selectionArgs,
                null
        );

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phoneCursor.close();
        }
        return number;
    }

    private void initRecyclerView() {
        adapter = new RecyclerView.Adapter<>() {

            public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                super.onViewRecycled(holder);
                ImageView iv_image = holder.itemView.findViewById(R.id.iv_image);
                Glide.with(holder.itemView.getContext()).clear(iv_image);
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
                return new RecyclerView.ViewHolder(view) {

                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
                TextView tv_phone = holder.itemView.findViewById(R.id.tv_phone);
                ImageView iv_image = holder.itemView.findViewById(R.id.iv_image);
                PhoneContact contact = dataList.get(position);
                tv_name.setText(contact.getName());
                tv_phone.setText(contact.getPhone());

                Glide.with(holder.itemView.getContext()).clear(iv_image);

                iv_image.setTag(R.id.tag_glide_holder, position);
                Glide.with(holder.itemView.getContext()).load(contact.getAvatar())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                int position = holder.getAdapterPosition();
                                int previousPosition = (int) iv_image.getTag(R.id.tag_glide_holder);
                                if (position == previousPosition) {
                                    iv_image.setImageDrawable(resource);
                                }
                            }

                        });

            }

            @Override
            public int getItemCount() {
                return dataList.size();
            }

        };
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 20);
        binding.recyclerview.setRecycledViewPool(pool);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setItemViewCacheSize(20);
        binding.recyclerview.setNestedScrollingEnabled(false);
        RecyclerView.ItemAnimator itemAnimator = binding.recyclerview.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) (binding.recyclerview.getItemAnimator())).setSupportsChangeAnimations(false);
        }
        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Glide.with(ContentProviderActivity.this).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(ContentProviderActivity.this).resumeRequests();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        new RecyclerView.RecycledViewPool();

    }

}
