package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.NativeSQLiteDriver

internal actual fun sqliteDriver(): SQLiteDriver = NativeSQLiteDriver()
