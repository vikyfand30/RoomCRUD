package com.example.tooltrackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tooltrackapp.db.ToolsDao
import com.example.tooltrackapp.db.ToolsRoomDatabase
import com.example.tooltrackapp.model.Tools
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.list_item.*

class EditActivity : AppCompatActivity() {


    val EDIT_TOOLS_EXTRA = "edit_tools_extra"
    private lateinit var  tools: Tools
    private var isUpdate = false
    private lateinit var database: ToolsRoomDatabase
    private lateinit var dao: ToolsDao




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        database = ToolsRoomDatabase.getDatabase(applicationContext)
        dao = database.getToolsDao()


        if (intent.getParcelableExtra<Tools>(EDIT_TOOLS_EXTRA) != null){
                btn_delete.visibility = View.VISIBLE
                isUpdate = true
                tools = intent.getParcelableExtra(EDIT_TOOLS_EXTRA)
                tv_input_name.setText(tools.item)
                tv_input_borrower.setText(tools.friends)
                tv_input_qty.setText(tools.qty)
                tv_input_borrower_qty.setText(tools.get)

            }

          btn_save.setOnClickListener{
              val item = tv_input_name.text.toString()
              val friends = tv_input_borrower.text.toString()
              val qty = tv_input_qty.text.toString()
              val get = tv_input_borrower_qty.text.toString()

              if (item.isEmpty() && friends.isEmpty() && qty.isEmpty() && get.isEmpty()){
                  Toast.makeText(applicationContext, "Note cannot be empty", Toast.LENGTH_SHORT).show()

              }
              else{
                  if (isUpdate){
                      saveTools(Tools(id = tools.id, item = item, qty = qty , friends = friends , get = get ))
                  }
                  else{
                      saveTools(Tools(item = item,qty = qty , friends = friends , get = get  ))
                  }
              }

              finish()
          }

        btn_delete.setOnClickListener {
            deleteTools(tools)
            finish()
        }
    }

 private fun saveTools(tools: Tools){
     if(dao.getById(tools.id).isEmpty()){
         dao.insert(tools)

     } else{
         dao.update(tools)

     }

     Toast.makeText(applicationContext, "Tools Saved", Toast.LENGTH_SHORT).show()


 }


    private fun deleteTools(tools: Tools){
        dao.delete(tools)
        Toast.makeText(applicationContext, "Tools Delete", Toast.LENGTH_SHORT).show()
    }

}