package com.example.telefonskiimenik.base

import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.telefonskiimenik.R

open class BaseActivity: AppCompatActivity() {

    lateinit var screenTitle: TextView
    lateinit var imageBackButton: ImageButton

    override fun setContentView(layoutResID: Int) {
        val baseLayout: LinearLayout = layoutInflater.inflate(R.layout.activity_base, null) as LinearLayout
        val activityContainer: FrameLayout = baseLayout.findViewById(R.id.layout_container)
        screenTitle = baseLayout.findViewById(R.id.text_screen_title) as TextView
        imageBackButton = baseLayout.findViewById(R.id.image_back_button)

        layoutInflater.inflate(layoutResID, activityContainer, true)
        setupView()

        super.setContentView(baseLayout)
    }

    /**
     * Private methods
     */
    private fun setupView() {
        imageBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * Public methods
     */
    fun setScreenTitle(title: String) {
        screenTitle.text = title
    }

    fun getBackButton(): ImageButton {
        return imageBackButton
    }

}