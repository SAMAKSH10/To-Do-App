package com.example.to_do_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fab = findViewById<ImageView>(R.id.addbtn)
        val dataList = ArrayList<String>()
        var x=1
        val listview=findViewById<ListView>(R.id.items_list)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList)

        //Adding click listener for FAB
        fab.setOnClickListener { view ->
            //Show Dialog here to add new Item
            addNewItemDialog(listview,dataList,adapter,x)
            x++
            listview.adapter = adapter
        }

    }
    private fun addNewItemDialog(listview:ListView,dataList:ArrayList<String>,adapter:ArrayAdapter<String>,x:Int) {
        val alert = AlertDialog.Builder(this)
        val itemEditText = EditText(this)
        alert.setMessage("Add New Item")
        alert.setTitle("Enter To Do Item Text")
        alert.setView(itemEditText)
        alert.setPositiveButton("Submit") { dialog, positiveButton ->
            val enteredData="Task "+x.toString()+" is "+itemEditText.text.toString()
            dataList.add(enteredData)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss() // Close the dialog without doing anything.
        }
        alert.show()

    }
}