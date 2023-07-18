package ai.nivarthana.ageinmins

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onDateClick()
    }

    private fun onDateClick() {
        val datePicker: Button = findViewById(R.id.DatePicker)
        val selectedDate: TextView = findViewById(R.id.selectedDate)
        val ageInMinutesTextView: TextView = findViewById(R.id.ageInMinutes)
        //load the date picker on click of button
        //set value to selected Date
        datePicker.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedCalendar.time)

                selectedDate.text = formattedDate
                val currentDate = Calendar.getInstance()
                val ageInMinutes = (currentDate.timeInMillis - selectedCalendar.timeInMillis) / (60 * 1000)

                ageInMinutesTextView?.text = ageInMinutes.toString()

            }, year, month, day)

            dpd.show()

            //disable current date and future dates
            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

            ageInMinutesTextView?.let {
                it.text = null // Added to handle potential null pointer exception
            }
        }
    }

}
