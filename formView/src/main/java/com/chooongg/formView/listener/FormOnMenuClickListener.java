package com.chooongg.formView.listener;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.chooongg.formView.item.AbstractFormItem;

public interface FormOnMenuClickListener {
    void onFormMenuClick(@NonNull View view, @NonNull View menuView, @NonNull MenuItem menuItem, @NonNull AbstractFormItem<?> item);
}
