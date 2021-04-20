package com.github.kongpf8848.androidworld.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class FlingBehavior @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null
) : AppBarLayout.Behavior(context, attrs) {

    private var a = false

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {

        var velocityY=velocityY
        var consumed=consumed

        if (velocityY > 0.0f && !a || velocityY < 0.0f && a) {
            velocityY *= -1.0f
        }
        if (target is RecyclerView && velocityY < 0.0f) {
            consumed = target.getChildAdapterPosition(target.getChildAt(0)) > 3
        }
        return super.onNestedFling(
            coordinatorLayout,
            child,
            target,
            velocityX,
            velocityY,
            consumed
        )
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)
        a = dy > 0
    }
}