package com.silali.blueprint.views

import android.content.Context
import android.util.AttributeSet;
import android.view.Gravity
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat.setBackground
import com.silali.blueprint.R
//import android.R
import android.R.attr.elevation
import android.R.attr.shadowColor


class RoundLinerLayoutNormal : LinearLayout {
    constructor(context: Context) : super(context) {
        initBackground()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initBackground()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initBackground()
    }

    private fun initBackground() {
        background = ViewUtils.generateBackgroundWithShadow(
            this, R.color.white,
            R.dimen.radius_corner, R.color.shadowColor, R.dimen.elevation, Gravity.BOTTOM
        )
    }
}
