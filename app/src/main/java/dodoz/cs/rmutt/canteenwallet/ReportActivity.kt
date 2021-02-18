package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dodoz.cs.rmutt.canteenwallet.Adapter.ReportAdapter
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.model.userRecy
import kotlinx.android.synthetic.main.activity_report.*
import retrofit2.*

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        toolbar.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


//        RetrofitClient.instance.fetchAllUsers()
//            .enqueue(object : Callback<List<userRecy>> {
//                override fun onResponse(
//                    call: Call<List<userRecy>>,
//                    response: Response<List<userRecy>>,
//                ) {
////                    Log.d("ABC","onResponse : ${response.body()!![0].Date}")
////                    Log.d("ABC","onResponse : ${response.body()!![0].Typetransfer}")
////                    Log.d("ABC","onResponse : ${response.body()!![0].Amount}")
////                    Log.d("ABC","onResponse : ${response.body()!![0].EndAccID}")
////                    Toast.makeText(applicationContext, "Online", Toast.LENGTH_LONG).show()
//                    showData(response.body()!!)
//                }
//
//                override fun onFailure(call: Call<List<userRecy>>, t: Throwable) {
//                    Log.d("ABC","onFailure")
//                }
//
//            })

        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val walletid = sharedPrefManager.getString("wallet_id", "")

//        Toast.makeText(applicationContext, walletid, Toast.LENGTH_LONG).show()

        RetrofitClient.instance.Report(walletid!!)
            .enqueue(object : Callback<List<userRecy>> {
                override fun onResponse(
                    call: Call<List<userRecy>>,
                    response: Response<List<userRecy>>,
                ) {
//                    Log.d("ABC","onResponse : ${response.body()!![0].Date}")
//                    Log.d("ABC","onResponse : ${response.body()!![0].Typetransfer}")
//                    Log.d("ABC","onResponse : ${response.body()!![0].Amount}")
//                    Log.d("ABC","onResponse : ${response.body()!![0].EndAccID}")
//                    Toast.makeText(applicationContext, "Online", Toast.LENGTH_LONG).show()
                    showData(response.body()!!)
//                    g2()
                }

                override fun onFailure(call: Call<List<userRecy>>, t: Throwable) {
                    Log.d("ABC","onFailure")
                }

            })



//        val users = mutableListOf<userRecy>()
//        for (i in 0..100){
//            users.add(userRecy("2021-02-06 16:28:34","transfer","0.00"))
//        }

    }
    private fun showData(users:List<userRecy>){


        report_requestlist.apply {
            layoutManager = LinearLayoutManager(this@ReportActivity)
            adapter = ReportAdapter(users)
        }

    }

    private fun g2(){
        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val walletid = sharedPrefManager.getString("wallet_id", "")

        RetrofitClient.instance.Reportdw(walletid!!)
            .enqueue(object : Callback<List<userRecy>> {
                override fun onResponse(
                    call: Call<List<userRecy>>,
                    response: Response<List<userRecy>>,
                ) {
//                    Log.d("ABC","onResponse : ${response.body()!![0].Date}")
//                    Log.d("ABC","onResponse : ${response.body()!![0].Typetransfer}")
//                    Log.d("ABC","onResponse : ${response.body()!![0].Amount}")
//                    Log.d("ABC","onResponse : ${response.body()!![0].EndAccID}")
//                    Toast.makeText(applicationContext, "Online", Toast.LENGTH_LONG).show()
                    showData(response.body()!!)
                }

                override fun onFailure(call: Call<List<userRecy>>, t: Throwable) {
                    Log.d("ABC","onFailure")
                }

            })
    }

}