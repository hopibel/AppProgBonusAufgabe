package hopibel.todoapp

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel = hiltViewModel()) {
    NoteScreen()
}

// view 1: note list
@Composable
private fun NoteScreen() {
    var showCreateDialog by rememberSaveable { mutableStateOf(false) }

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
        // TODO: note list

        // note creation dialog
        if (showCreateDialog) {
            NoteCreationDialog(onDismissRequest = { showCreateDialog = false })
        }
    }
}

