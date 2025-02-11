package com.github.panpf.auto.release.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform