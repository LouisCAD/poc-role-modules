package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

internal actual fun sqliteDriver(): SQLiteDriver = BundledSQLiteDriver()
