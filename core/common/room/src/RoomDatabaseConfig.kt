package com.louiscad.poc.rolemodules.core.common.room

import androidx.room3.RoomDatabase

expect class RoomDatabaseConfig(databaseNameOrPath: String) {
    inline fun <reified T : RoomDatabase> toDatabaseBuilder(): RoomDatabase.Builder<T>
}
