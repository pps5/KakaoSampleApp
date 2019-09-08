package io.github.pps5.kakaosampleapp.data.store

import androidx.room.*
import io.github.pps5.kakaosampleapp.data.converter.LinkConverter
import io.github.pps5.kakaosampleapp.data.converter.ZonedDateTimeConverter
import io.github.pps5.kakaosampleapp.data.entity.Entry

@Database(entities = [Entry::class], version = 1, exportSchema = false)
@TypeConverters(ZonedDateTimeConverter::class, LinkConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun entryDao(): EntryDao
}

@Dao
interface EntryDao {

    @Query("SELECT * FROM entry")
    fun getAll(): List<Entry>

    @Insert
    fun addAll(entryList: List<Entry>)

    @Query("DELETE FROM entry")
    fun deleteAll()
}