package com.app.employeedirectory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EmployeeEntity::class],
    version = 1
)
abstract class EmployeeDatabase: RoomDatabase() {
    abstract val dao: EmployeeDao
}