package hopibel.todoapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import hopibel.todoapp.data.db.Note

@Composable
fun NoteScreen(viewModel: NoteViewModel = hiltViewModel()) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    NoteScreen(notes)
}

// view 1: note list
@Composable
private fun NoteScreen(notes: List<Note>) {
    var showCreateDialog by rememberSaveable { mutableStateOf(false) }

    var showNoteDialog by rememberSaveable { mutableStateOf(false) }
    var selectedNoteId by rememberSaveable { mutableStateOf(0L) }

    Scaffold(
        // add button
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { FloatingActionButton(onClick = { showCreateDialog = true }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "add"
            )
        }}
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()) {
                items(notes.sortedByDescending { it.created.time }) { note ->
                    NoteListElement(note, onClick = {
                        showNoteDialog = true
                        selectedNoteId = note.id
                    })
                }
            }
        }

        // note creation dialog
        if (showCreateDialog) {
            NoteCreationDialog(onDismissRequest = { showCreateDialog = false })
        } else if (showNoteDialog) {
            NoteDisplayDialog(noteId = selectedNoteId, onDismissRequest = { showNoteDialog = false })
        }
    }
}

@Composable
fun NoteListElement(note: Note, onClick: (note: Note) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick(note) }
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = note.title,
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.height(0.5.dp))
            Text(
                // preview first line of body
                note.body,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}