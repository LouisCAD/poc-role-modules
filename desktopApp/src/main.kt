package com.louiscad.poc.rolemodules

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "poc-role-modules") {
        App()
    }
}
