package com.example.testtablayout

import android.annotation.SuppressLint
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {
    var userFullNameEdt: EditText? = null
    var userEmailEdt:EditText? = null
    var userAgeEdt:EditText? = null
    var userPhoneEdt:EditText? = null
    var dbHandler: DBHandler? = null
    var addUserBtn: Button? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_first, container, false)
        userFullNameEdt = v.findViewById(R.id.full_name)
        userEmailEdt = v.findViewById(R.id.email)
        userAgeEdt = v.findViewById(R.id.age)
        userPhoneEdt = v.findViewById(R.id.phone)
        dbHandler = DBHandler(context)
        addUserBtn = v.findViewById(R.id.addBtn)

        addUserBtn!!.setOnClickListener(View.OnClickListener {
            val userFullName: String = userFullNameEdt!!.getText().toString()
            val userEmail: String = userEmailEdt!!.getText().toString()
            val userAge: String = userAgeEdt!!.getText().toString()
            val userPhone: String = userPhoneEdt!!.getText().toString()

            if (userFullName.isEmpty() || userEmail.isEmpty() || userAge.isEmpty() || userPhone.isEmpty()) {
                Toast.makeText(context, "Please enter all the data..", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            dbHandler!!.addNewUser(userFullName, userEmail, userAge, userPhone)

            Toast.makeText(context, "Users Has Been Added Succesfully", Toast.LENGTH_SHORT).show()
            userFullNameEdt!!.setText("")
            userEmailEdt!!.setText("")
            userAgeEdt!!.setText("")
            userPhoneEdt!!.setText("")

        })

        return v

    }

}
