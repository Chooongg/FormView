package com.chooongg.formView.itemProvider

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.updateLayoutParams
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.item.AbstractFormOptionItem
import com.chooongg.formView.option.FormArrayAdapter
import com.chooongg.formView.part.AbstractFormPart
import com.chooongg.formView.style.AbstractFormStyle
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.min

class FormInputProvider : AbstractFormOptionItemProvider() {
    override fun onCreateViewHolder(
        part: AbstractFormPart<*>, style: AbstractFormStyle, parent: ViewGroup
    ): View {
        return TextInputLayout(
            parent.context,
            null,
            com.google.android.material.R.attr.textInputOutlinedExposedDropdownMenuStyle
        ).also {
            it.id = R.id.formContentView
            val editText = MaterialAutoCompleteTextView(
                ContextThemeWrapper(
                    it.context,
                    com.google.android.material.R.style.ThemeOverlay_Material3_AutoCompleteTextView
                )
            ).apply {
                id = R.id.formContentSecondView
                isHorizontalFadingEdgeEnabled = true
                isVerticalFadingEdgeEnabled = true
                setFadingEdgeLength(resources.getDimensionPixelSize(R.dimen.formFadingEdgeLength))
                setTextAppearance(formTextAppearance(R.attr.formTextAppearanceContent))
                setPaddingRelative(
                    style.padding.startMedium,
                    style.padding.topMedium,
                    style.padding.endMedium,
                    style.padding.bottomMedium
                )
            }
            it.addView(editText)
            val fontHeight = FormUtils.getFontRealHeight(editText)
            it.setHintTextAppearance(it.formTextAppearance(R.attr.formTextAppearanceHint))
            it.setPrefixTextAppearance(it.formTextAppearance(R.attr.formTextAppearancePrefix))
            it.setSuffixTextAppearance(it.formTextAppearance(R.attr.formTextAppearanceSuffix))
            it.placeholderTextAppearance =
                it.formTextAppearance(R.attr.formTextAppearancePlaceholder)
            it.setCounterTextAppearance(
                it.formTextAppearance(R.attr.formTextAppearanceCounter)
            )
            it.setCounterOverflowTextAppearance(
                it.formTextAppearance(R.attr.formTextAppearanceCounter)
            )
            it.tag = editText.textColors
            // StartIcon
            it.findViewById<CheckableImageButton>(
                com.google.android.material.R.id.text_input_start_icon
            ).apply {
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                minimumHeight = 0
                minimumWidth = 0
                setPaddingRelative(
                    style.padding.startMedium,
                    style.padding.topMedium,
                    style.padding.endMedium,
                    style.padding.bottomMedium
                )
                updateLayoutParams<MarginLayoutParams> {
                    marginEnd = 0
                    width = fontHeight + style.padding.startMedium + style.padding.endMedium
                    height = fontHeight + style.padding.topMedium + style.padding.bottomMedium
                }
                val a =
                    context.obtainStyledAttributes(intArrayOf(com.google.android.material.R.attr.colorControlHighlight))
                val color = a.getColorStateList(0) ?: ColorStateList.valueOf(Color.GRAY)
                a.recycle()
                background =
                    RippleDrawable(color, null, LayerDrawable(arrayOf(ShapeDrawable().apply {
                        paint.color = Color.GRAY
                        shape = OvalShape()
                    })).apply {
                        setLayerGravity(0, Gravity.END or Gravity.CENTER_VERTICAL)
                        val size =
                            fontHeight + min(style.padding.startMedium, style.padding.endMedium) * 2
                        setLayerWidth(0, size)
                        setLayerHeight(0, size)
                    })
            }
            // EndIcon
            it.setEndIconTintList(editText.hintTextColors)
            it.findViewById<CheckableImageButton>(
                com.google.android.material.R.id.text_input_end_icon
            ).apply {
                background = null
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                minimumHeight = 0
                minimumWidth = 0
                setPaddingRelative(
                    style.padding.startMedium,
                    style.padding.topMedium,
                    style.padding.endMedium,
                    style.padding.bottomMedium
                )
                val realWidth = fontHeight + style.padding.startMedium + style.padding.endMedium
                updateLayoutParams<MarginLayoutParams> {
                    marginStart = 0
                    width = realWidth
                    height = fontHeight + style.padding.topMedium + style.padding.bottomMedium
                }
                editText.setAdapter(
                    FormArrayAdapter<CharSequence>(
                        FormBoundary(
                            style.padding.startMedium,
                            style.padding.topMedium,
                            realWidth,
                            style.padding.bottomMedium
                        )
                    )
                )
            }
            it.isHintEnabled = false
            it.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
            it.boxStrokeWidth = 0
            it.boxStrokeWidthFocused = 0
            it.setPaddingRelative(0, 0, 0, 0)
            it.getChildAt(0).updateLayoutParams<LinearLayout.LayoutParams> {
                topMargin = 0
            }
        }
    }

    override fun onBindViewHolder(holder: FormViewHolder, item: AbstractFormItem<*>) {
        with(holder.getView<MaterialAutoCompleteTextView>(R.id.formContentSecondView)) {
//            hint = when (i)
        }
        loadOption(holder, item)
    }

    override fun onBindViewHolderOther(
        holder: FormViewHolder, item: AbstractFormItem<*>, payload: Any
    ) {
        if (payload == AbstractFormOptionItem.CHANGE_OPTION_PAYLOAD_FLAG) {

        }
    }

    protected fun configOption(holder: FormViewHolder, item: AbstractFormItem<*>) {
        with(holder.getView<MaterialAutoCompleteTextView>(R.id.formContentSecondView)) {

        }
    }
}