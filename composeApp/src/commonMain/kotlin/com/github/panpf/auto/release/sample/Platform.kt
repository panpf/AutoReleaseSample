package com.github.panpf.auto.release.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform