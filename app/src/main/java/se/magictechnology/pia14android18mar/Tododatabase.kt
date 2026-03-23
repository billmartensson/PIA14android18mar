package se.magictechnology.pia14android18mar

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class Todoitem(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "todotitle") var todotitle: String,
    @ColumnInfo(name = "done") var done: Boolean
)

@Dao
interface TodoitemDao {

    @Query("SELECT * FROM todoitem ORDER BY done ASC")
    fun getAllTodo() : List<Todoitem>

    @Query("SELECT * FROM todoitem WHERE done = 1")
    fun getDone() : List<Todoitem>

    @Query("SELECT * FROM todoitem WHERE done = 0")
    fun getNotDone() : List<Todoitem>

    @Insert
    fun addTodo(todoitem: Todoitem)

    @Delete
    fun deleteTodo(todoitem: Todoitem)

    @Update
    suspend fun updateTodo(todoitem: Todoitem)



    @Query("DELETE FROM todoitem WHERE done = 1")
    fun deleteDone()

    @Query("UPDATE todoitem SET done = 0")
    fun setallnotdone()

}



@Database(
    entities = [Todoitem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoitemdao(): TodoitemDao
}