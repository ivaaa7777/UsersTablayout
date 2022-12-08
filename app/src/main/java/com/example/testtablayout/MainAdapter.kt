package com.example.testtablayout

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException

class MainAdapter(var activity: SecondFragment, var jsonArray: JSONArray) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var databaseHelper: DBHandler? = null
    fun updateArray(jsonArray: JSONArray) {
        this.jsonArray = jsonArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list, parent, false)
        databaseHelper = DBHandler(view.context)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val `object` = jsonArray.getJSONObject(position)
            holder.userFullNameTxt.text = `object`.getString("name")
            holder.userEmailTxt.text = `object`.getString("email")
            holder.userAgeTxt.text = `object`.getString("age")
            holder.userPhoneTxt.text = `object`.getString("phone")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return jsonArray.length()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userFullNameTxt: TextView
        var userEmailTxt: TextView
        var userAgeTxt: TextView
        var userPhoneTxt: TextView

        init {
            userFullNameTxt = itemView.findViewById(R.id.userFullNameTxt)
            userEmailTxt = itemView.findViewById(R.id.userEmailTxt)
            userAgeTxt = itemView.findViewById(R.id.userAgeTxt)
            userPhoneTxt = itemView.findViewById(R.id.userPhoneTxt)
        }
    }
}