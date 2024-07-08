package com.google.android.flexbox

object FlexInternalFunction {
    fun getPositionToFlexLineIndex(flexboxLayoutManager: FlexboxLayoutManager, position: Int): Int {
        return flexboxLayoutManager.getPositionToFlexLineIndex(position)
    }
}