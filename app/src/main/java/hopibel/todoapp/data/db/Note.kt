package hopibel.todoapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val body: String,
    val created: Date,
)