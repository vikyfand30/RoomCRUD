package com.example.tooltrackapp.db

import androidx.room.*
import com.example.tooltrackapp.model.Tools

@Dao
interface ToolsDao {

    @Insert
    fun insert(tools:Tools)

    @Update
    fun update(tools: Tools)

    @Delete
    fun delete(tools: Tools)

    @Query("SELECT * FROM tools")
    fun getAll() : List<Tools>

    @Query("SELECT * FROM tools WHERE id = :id")
    fun getById(id: Int) : List<Tools>


}