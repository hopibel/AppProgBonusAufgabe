package hopibel.todoapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel

// view 2: note creation
@Composable
fun NoteCreationDialog(onDismissRequest: () -> Unit) {
    var titleText by remember { mutableStateOf("") }
    var bodyText by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                OutlinedTextField(
                    value = titleText,
                    onValueChange = { titleText = it },
                    label = { Text("New note") },
                    placeholder = { Text("Title") },
                    singleLine = true,
                )
                OutlinedTextField(
                    value = bodyText,
                    onValueChange = { bodyText = it },
                    label = { Text("Body") },
                    maxLines = 10,
                    modifier = Modifier.height(230.dp),
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = { /*viewModel.addNote(titleText, bodyText)*/ }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}