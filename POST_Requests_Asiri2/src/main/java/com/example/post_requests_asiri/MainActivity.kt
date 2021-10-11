package com.example.post_requests_asiri


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val responseText = findViewById<View>(R.id.textView) as TextView
        val button = findViewById<View>(R.id.button3) as Button

        button.setOnClickListener {
            intent = Intent(applicationContext, NewUser::class.java)
            startActivity(intent)
        }
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            val progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            if (apiInterface != null) {
                apiInterface.getUser()?.enqueue(object : Callback<List<Users.UserDetails>> {

                    override fun onResponse(
                        call: Call<List<Users.UserDetails>>,
                        response: Response<List<Users.UserDetails>>
                    ) {
                        progressDialog.dismiss()
                        var NewPost: String? = "";
                        for (User in response.body()!!) {
                            NewPost =
                                NewPost + User.name + "\n" + User.location + "\n" + "\n"
                        }
                        responseText.text = NewPost
                    }

                    override fun onFailure(call: Call<List<Users.UserDetails>>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT)
                            .show()}
                })
            }
        }
    }


/*
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun addBook(){
            APIInterface.addBook(Book("William","Hamlet")).enqueue(object : Handler.Callback<Book>){

            }
        }
    }
}

 */