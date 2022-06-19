package hopibel.todoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// required by hilt
@HiltAndroidApp
class NoteApplication: Application()