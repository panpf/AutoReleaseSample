package com.github.panpf.auto.release.compose

import com.github.panpf.auto.release.core.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}