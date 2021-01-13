package com.example.tooltrackapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tooltrackapp.db.ToolsRoomDatabase
import com.example.tooltrackapp.model.Tools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Method get tools data
        getToolsData()

        //Declare floating button intent
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }

    }

    private fun getToolsData(){
        //Declare database Room
        val database = ToolsRoomDatabase.getDatabase(applicationContext)
        val dao = database.getToolsDao()
        //Setup recyclerview
        val listItems = arrayListOf<Tools>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text.visibility = View.GONE
        }
        else {
            text.visibility = View.VISIBLE
        }

    }

    private fun setupRecyclerView(listItems : ArrayList<Tools>){
        recycler_view_main.apply {
            adapter = ToolsAdapter (listItems, object : ToolsAdapter.ToolsListener{
                override fun OnItemClicked(tools: Tools) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_TOOLS_EXTRA, tools)
                    startActivity(intent)

                }
            })

            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    //Get data when page opened again
    override fun onResume() {
        super.onResume()
        getToolsData()
    }


}