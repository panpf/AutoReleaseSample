package com.github.panpf.auto.release.sample

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.panpf.auto.release.compose.AutoRelease
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AutoRelease()
    }
}