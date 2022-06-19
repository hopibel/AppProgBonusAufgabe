package hopibel.todoapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import hopibel.todoapp.data.db.Note
import java.util.*

// view 3: display note
@Composable
fun NoteDisplayDialog(noteId: Long, onDismissRequest: () -> Unit, viewModel: NoteViewModel = hiltViewModel()) {
    val scroll = rememberScrollState(0)

    val note by viewModel.getNote(noteId).collectAsState(Note(0, "", "", Date()))
    val title = note.title
    val body = note.body

    Dialog(onDismissRequest = onDismissRequest) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    title,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(0.5.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .verticalScroll(scroll)
                ) {
                    Text(body)
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {
                        onDismissRequest()
                        viewModel.deleteNote(note)
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}