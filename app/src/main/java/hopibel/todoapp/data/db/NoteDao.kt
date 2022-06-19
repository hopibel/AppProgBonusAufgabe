package hopibel.todoapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :noteId")
    fun getNote(noteId: Long): Flow<Note>

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}