package com.example.finaljson

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var curencyDetails: Datum? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userinp = findViewById<View>(R.id.userinput) as EditText
        val convrt = findViewById<View>(R.id.btn) as Button
        val spinner = findViewById<View>(R.id.spr) as Spinner

        val cur = arrayListOf("inr", "usd", "aud", "sar", "cny", "jpy")

        var selected: Int = 0

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, cur
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    selected = position
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        convrt.setOnClickListener {

            var sel = userinp.text.toString()
            var currency: Double = sel.toDouble()

            getCurrency(onResult = {
                curencyDetails = it

                when (selected) {
                    0 -> disp(calc(curencyDetails?.eur?.inr?.toDouble(), currency));
                    1 -> disp(calc(curencyDetails?.eur?.usd?.toDouble(), currency));
                    2 -> disp(calc(curencyDetails?.eur?.aud?.toDouble(), currency));
                    3 -> disp(calc(curencyDetails?.eur?.sar?.toDouble(), currency));
                    4 -> disp(calc(curencyDetails?.eur?.cny?.toDouble(), currency));
                    5 -> disp(calc(curencyDetails?.eur?.jpy?.toDouble(), currency));
                }
            })
        }

    }

    private fun disp(calc: Double) {

        val responseText = findViewById<View>(R.id.textView3) as TextView

        responseText.text = "result " + calc
    }

    private fun calc(i: Double?, sel: Double): Double {
        var s = 0.0
        if (i != null) {
            s = (i * sel)
        }
        return s
    }

    private fun getCurrency(onResult: (Datum?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        if (apiInterface != null) {
            apiInterface.getCurrency()?.enqueue(object : Callback<Datum> {
                override fun onResponse(
                    call: Call<Datum>,
                    response: Response<Datum>
                ) {
                    onResult(response.body())

                }

                override fun onFailure(call: Call<Datum>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}

/*

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val responseText = findViewById<View>(R.id.text) as TextView
        val tvdate = findViewById<View>(R.id.tvdate) as TextView
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val call: Call<BookDetails?>? = apiInterface!!.doGetListResources()

        call?.enqueue(object : Callback<BookDetails?> {
            override fun onResponse(
                call: Call<BookDetails?>?,
                response: Response<BookDetails?>
            ) {
                Log.d("TAG", response.code().toString() + "")
                var displayResponse = ""
                val resource: BookDetails? = response.body()
                val datumList = resource?.eur
                val date = resource?.date

               // for (datum in datumList!!)
                    displayResponse += """${eur.ada.toString()} ${datum.aed.toString()} ${datum.afn.toString()}"""

                tvdate.text=date
                responseText.text = displayResponse
            }

            override fun onFailure(call: Call<BookDetails?>, t: Throwable?) {
                call.cancel()
            }
        })



    }

}








//displayResponse += """${text.toString()} Page
//$total Total
//$totalPages Total Pages//"""

 */