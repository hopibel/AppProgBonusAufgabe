package hopibel.todoapp.data

import hopibel.todoapp.data.db.Note
import hopibel.todoapp.data.db.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    val allNotes: Flow<List<Note>> = noteDao.getNotes()

    suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }
}
