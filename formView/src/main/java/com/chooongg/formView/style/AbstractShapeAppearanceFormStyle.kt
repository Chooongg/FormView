package com.chooongg.formView.style

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.R
import com.chooongg.formView.config.AbstractFormStyleConfig
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.ktx.attrResourcesId
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.ShapeAppearanceModel

abstract class AbstractShapeAppearanceFormStyle(config: AbstractFormStyleConfig) :
    AbstractFormStyle(config) {

    protected lateinit var shapeAppearanceModel: ShapeAppearanceModel

    override fun onCreateStyle(parent: ViewGroup): ViewGroup? {
        if (!this::shapeAppearanceModel.isInitialized) {
            val resId = parent.attrResourcesId(
                R.attr.formShapeAppearanceCorner, parent.attrResourcesId(
                    com.google.android.material.R.attr.shapeAppearanceCornerMedium, 0
                )
            )
            shapeAppearanceModel = ShapeAppearanceModel.builder(parent.context, resId, 0).build()
        }
        return null
    }

    fun getShapeAppearanceModel(view: View, item: AbstractFormItem<*>) =
        ShapeAppearanceModel.builder().apply {
            if (view.layoutDirection != View.LAYOUT_DIRECTION_RTL) {
                setTopLeftCornerSize(
                    if (item.boundary.top != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.topLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setTopRightCornerSize(
                    if (item.boundary.top != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.topRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomLeftCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.bottomLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomRightCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.bottomRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
            } else {
                setTopLeftCornerSize(
                    if (item.boundary.top != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.topRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setTopRightCornerSize(
                    if (item.boundary.top != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.topLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomLeftCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.start != 0) {
                        shapeAppearanceModel.bottomRightCornerSize
                    } else AbsoluteCornerSize(0f)
                )
                setBottomRightCornerSize(
                    if (item.boundary.bottom != 0 && item.boundary.end != 0) {
                        shapeAppearanceModel.bottomLeftCornerSize
                    } else AbsoluteCornerSize(0f)
                )
            }
        }.build()
}