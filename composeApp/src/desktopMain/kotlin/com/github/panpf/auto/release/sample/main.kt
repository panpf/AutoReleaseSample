package com.github.panpf.auto.release.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "AutoReleaseSample",
    ) {
        App()
    }
}