package com.example.testtablayout

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    var databaseHelper: DBHandler? = null
    var adapter: MainAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        recyclerView = v.findViewById(R.id.recycler_view)
        databaseHelper = DBHandler(context)

        recyclerView!!.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(this,databaseHelper!!.getArray())
        recyclerView!!.adapter = adapter

        adapter!!.updateArray(databaseHelper!!.getArray())
        return v
    }


}