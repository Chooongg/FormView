package com.chooongg.formView.data

import androidx.annotation.IntDef

data class FormBoundary(
    @BoundaryInt var start: Int,
    @BoundaryInt val top: Int,
    @BoundaryInt val end: Int,
    @BoundaryInt val bottom: Int
) {

    constructor() : this(NONE, NONE, NONE, NONE)

    companion object {
        const val NONE = 0
        const val MIDDLE = 1
        const val GLOBAL = 2
    }

    @IntDef(NONE, MIDDLE, GLOBAL)
    annotation class BoundaryInt

    fun update(
        @BoundaryInt start: Int = this.start,
        @BoundaryInt top: Int = this.top,
        @BoundaryInt end: Int = this.end,
        @BoundaryInt bottom: Int = this.bottom
    ): FormBoundary = FormBoundary(start, top, end, bottom)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FormBoundary) return false

        if (start != other.start) return false
        if (top != other.top) return false
        if (end != other.end) return false
        return bottom == other.bottom
    }

    override fun toString(): String {
        return "[start: ${start}, top:${top}, end:${end}, bottom:${bottom}]"
    }

    override fun hashCode(): Int {
        var result = start
        result = 31 * result + top
        result = 31 * result + end
        result = 31 * result + bottom
        return result
    }
}