package com.louiscad.poc.rolemodules

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
