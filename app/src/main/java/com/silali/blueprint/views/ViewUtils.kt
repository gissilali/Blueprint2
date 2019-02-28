package com.silali.blueprint.views

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.LAYER_TYPE_SOFTWARE

object ViewUtils {
    fun generateBackgroundWithShadow(
        view: View, @ColorRes backgroundColor: Int,
        @DimenRes cornerRadius: Int,
        @ColorRes shadowColor: Int,
        @DimenRes elevation: Int,
        shadowGravity: Int
    ): Drawable {
        val cornerRadiusValue = view.context.resources.getDimension(cornerRadius)
        val elevationValue = view.context.resources.getDimension(elevation).toInt()
        val shadowColorValue = ContextCompat.getColor(view.context, shadowColor)
        val backgroundColorValue = ContextCompat.getColor(view.context, backgroundColor)

        val outerRadius = floatArrayOf(
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue
        )

        val backgroundPaint = Paint()
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.setShadowLayer(cornerRadiusValue, 0f, 0f, 0)

        val shapeDrawablePadding = Rect()
        shapeDrawablePadding.left = elevationValue
        shapeDrawablePadding.right = elevationValue

        val dy: Int
        when (shadowGravity) {
            Gravity.CENTER -> {
                shapeDrawablePadding.top = elevationValue
                shapeDrawablePadding.bottom = elevationValue
                dy = 0
            }
            Gravity.TOP -> {
                shapeDrawablePadding.top = elevationValue * 2
                shapeDrawablePadding.bottom = elevationValue
                dy = -1 * elevationValue / 3
            }
            Gravity.BOTTOM -> {
                shapeDrawablePadding.top = elevationValue
                shapeDrawablePadding.bottom = elevationValue * 2
                dy = elevationValue / 3
            }
            else -> {
                shapeDrawablePadding.top = elevationValue
                shapeDrawablePadding.bottom = elevationValue * 2
                dy = elevationValue / 3
            }
        }

        val shapeDrawable = ShapeDrawable()
        shapeDrawable.setPadding(shapeDrawablePadding)

        shapeDrawable.paint.color = backgroundColorValue
        shapeDrawable.paint.setShadowLayer(cornerRadiusValue / 3, 0f, dy.toFloat(), shadowColorValue)

        view.setLayerType(1, shapeDrawable.paint)

        shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

        val drawable = LayerDrawable(arrayOf<Drawable>(shapeDrawable))
        drawable.setLayerInset(0, elevationValue, elevationValue * 2, elevationValue, elevationValue * 2)

        return drawable

    }
}
