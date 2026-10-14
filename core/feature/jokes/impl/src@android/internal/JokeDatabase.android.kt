package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.AndroidSQLiteDriver

internal actual fun sqliteDriver(): SQLiteDriver = AndroidSQLiteDriver()
