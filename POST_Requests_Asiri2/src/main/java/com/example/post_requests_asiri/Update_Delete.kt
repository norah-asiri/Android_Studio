package com.example.post_requests_asiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update_delete.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Update_Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)
        val etName = findViewById<View>(R.id.etName) as EditText
        val etLocation = findViewById<View>(R.id.etLocation) as EditText
        val userID = findViewById<View>(R.id.userID) as EditText
        val btupdate = findViewById<View>(R.id.btupdate) as Button
        val btdel = findViewById<View>(R.id.Delete) as Button

        btupdate.setOnClickListener {
            var f = Users.UserDetails(
                etName.text.toString(),
                etLocation.text.toString(),
                userID.text.toString().toInt()
            )
            updatUser(f, onResult = {
                etName.setText("")
                etLocation.setText("")
                userID.setText("")
                Toast.makeText(applicationContext, "Update Success!", Toast.LENGTH_SHORT).show()
            })
        }

        btdel.setOnClickListener {
            deleteUser(userID.text.toString().toInt())
        }

    }

    private fun updatUser(f: Users.UserDetails, onResult: () -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@Update_Delete)

        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface?.updateUser(
                userID.text.toString().toInt(),
                Users.UserDetails(
                    etName.text.toString(),
                    etLocation.text.toString(),
                    userID.text.toString().toInt()
                )
            )?.enqueue(object : Callback<Users.UserDetails> {
                override fun onResponse(
                    call: Call<Users.UserDetails>,
                    response: Response<Users.UserDetails>
                ) {
                    onResult()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<Users.UserDetails>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()
                }
            })
        }
    }

    private fun deleteUser(ID: Int?) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@Update_Delete)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if (apiInterface != null) {
            apiInterface?.deleteUser(userID.text.toString().toInt()
            )?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                   // onResult()
                    Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    //onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()
                }
            })
        }

    }
}