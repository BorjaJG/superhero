package com.example.superhero.core.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.superhero.R

class ErrorAppView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textError: TextView
    private val buttonRetry: Button

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.error_app_view, this, true)
        textError = findViewById(R.id.textError)
        buttonRetry = findViewById(R.id.buttonRetry)
    }

    fun render(error: ErrorAppUI) {
        visibility = VISIBLE
        textError.text = error.message
        buttonRetry.setOnClickListener { error.onRetry?.invoke() }
    }

    fun hide() {
        visibility = GONE
    }

    data class ErrorAppUI(
        val message: String,
        val onRetry: (() -> Unit)? = null
    )
}