package com.github.kongpf8848.androidworld.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class FlingBehavior @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null
) : AppBarLayout.Behavior(context, attrs) {

    private val TOP_CHILD_FLING_THRESHOLD = 3
    private var isPositive = false

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {

        Log.d("JACK8",
            "onNestedFling() called with: coordinatorLayout = $coordinatorLayout, child = $child, target = $target, velocityX = $velocityX, velocityY = $velocityY, consumed = $consumed"
        )
        var velocityY=velocityY
        var consumed=consumed

        if (velocityY > 0.0f && !isPositive || velocityY < 0.0f && isPositive) {
            velocityY *= -1.0f
        }
        if (target is RecyclerView && velocityY < 0.0f) {
            consumed = target.getChildAdapterPosition(target.getChildAt(0)) > TOP_CHILD_FLING_THRESHOLD
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
        Log.d(
            "JACK8",
            "onNestedPreScroll() called with: coordinatorLayout = $coordinatorLayout, child = $child, target = $target, dx = $dx, dy = $dy, consumed = $consumed"
        )
        isPositive = dy > 0
    }
}