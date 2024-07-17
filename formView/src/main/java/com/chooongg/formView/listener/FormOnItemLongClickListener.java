package com.chooongg.formView.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.chooongg.formView.item.AbstractFormItem;
import com.chooongg.formView.part.AbstractFormPart;

public interface FormOnItemLongClickListener {
    void onFormItemLongClick(@NonNull View view, @NonNull AbstractFormPart<?> part, @NonNull AbstractFormItem<?> item);
}
