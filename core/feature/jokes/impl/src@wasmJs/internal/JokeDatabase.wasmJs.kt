package com.louiscad.poc.rolemodules.core.feature.jokes.impl.internal

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import org.w3c.dom.Worker

internal actual fun sqliteDriver(): SQLiteDriver {
    return WebWorkerSQLiteDriver(Worker(TODO()))
}
