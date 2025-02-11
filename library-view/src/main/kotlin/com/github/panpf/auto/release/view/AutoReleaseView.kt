package com.github.panpf.auto.release.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.github.panpf.auto.release.core.getPlatform
import com.github.panpf.auto.release.view.databinding.ViewSampleBinding

class AutoReleaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        val binding = ViewSampleBinding.inflate(
            LayoutInflater.from(
                /* context = */ this@AutoReleaseView.context
            ),
            /* parent = */ this,
            /* attachToParent = */ true
        )
        binding.textView.text = "View: Hello, ${getPlatform().name}!"

        binding.imageView.isVisible = false
        binding.textView.isVisible = false

        binding.button.setOnClickListener {
            binding.imageView.isVisible = !binding.imageView.isVisible
            binding.textView.isVisible = !binding.textView.isVisible
        }
    }
}