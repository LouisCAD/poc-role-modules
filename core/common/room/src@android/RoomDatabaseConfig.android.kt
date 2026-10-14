package com.louiscad.poc.rolemodules.core.common.room

import androidx.room3.Room
import androidx.room3.RoomDatabase
import splitties.init.appCtx

actual class RoomDatabaseConfig actual constructor(val databaseNameOrPath: String) {
    actual inline fun <reified T : RoomDatabase> toDatabaseBuilder(): RoomDatabase.Builder<T> {
        return Room.databaseBuilder(
            context = appCtx,
            name = databaseNameOrPath
        )
    }
}
