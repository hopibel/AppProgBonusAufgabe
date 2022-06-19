package hopibel.todoapp

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel = viewModel()) {
    var showCreateDialog by rememberSaveable { mutableStateOf(false) }

    NoteScreen(
        showCreateDialog = showCreateDialog,
        onAddNoteClick = { showCreateDialog = true },
        onDismissCreate = { showCreateDialog = false },
    )
}

// view 1: note list
@Composable
private fun NoteScreen(showCreateDialog: Boolean, onAddNoteClick: () -> Unit, onDismissCreate: () -> Unit) {
    Scaffold(
        // add button
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { FloatingActionButton(onClick = onAddNoteClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "add"
            )
        }}
    ) {
        // TODO: note list

        // note creation dialog
        if (showCreateDialog) {
            NoteCreationDialog(onDismissRequest = onDismissCreate)
        }
    }
}

