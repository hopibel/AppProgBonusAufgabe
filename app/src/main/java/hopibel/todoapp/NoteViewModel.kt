package hopibel.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hopibel.todoapp.data.NoteRepository
import hopibel.todoapp.data.db.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    val notes: Flow<List<Note>> = noteRepository.allNotes

    fun addNote(title: String, body: String) {
        viewModelScope.launch {
            // pass 0 as id for autoincrement
            noteRepository.addNote(Note(
                id = 0,
                title,
                body,
                created = Date()
            ))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}
