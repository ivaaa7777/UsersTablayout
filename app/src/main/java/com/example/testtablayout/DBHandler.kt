package com.example.testtablayout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DBHandler(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    var TableName = "UsersTable"
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FULLNAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + AGE_COL + " TEXT,"
                + PHONE_COL + " TEXT)")
        db.execSQL(query)
    }
    fun addNewUser(userFullName: String?, userEmail: String?, userAge: String?, userPhone: String?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(FULLNAME_COL, userFullName)
        values.put(EMAIL_COL, userEmail)
        values.put(AGE_COL, userAge)
        values.put(PHONE_COL,userPhone)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getArray(): JSONArray {
        val database = readableDatabase
        val jsonArray = JSONArray()
        val sQuery = "select * from $TableName"
        val cursor = database.rawQuery(sQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val `object` = JSONObject()
                try {
                    `object`.put("id", cursor.getString(0))
                    `object`.put("name", cursor.getString(1))
                    `object`.put("email", cursor.getString(2))
                    `object`.put("age", cursor.getString(3))
                    `object`.put("phone", cursor.getString(4))
                    jsonArray.put(`object`)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return jsonArray
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "UsersDB"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "UsersTable"
        private const val ID_COL = "id"
        private const val FULLNAME_COL = "name"
        private const val EMAIL_COL = "email"
        private const val AGE_COL = "age"
        private const val PHONE_COL = "phone"
    }

}