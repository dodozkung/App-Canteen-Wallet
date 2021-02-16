//package dodoz.cs.rmutt.canteenwallet
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Base64
//import android.util.Log.d
//import android.widget.Toast
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.google.android.material.snackbar.Snackbar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import dodoz.cs.rmutt.canteenwallet.Adapter.ReportAdapter
//import dodoz.cs.rmutt.canteenwallet.Retrofit.Api
//import dodoz.cs.rmutt.canteenwallet.Retrofit.Apiservice
//import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
//import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
//import dodoz.cs.rmutt.canteenwallet.model.Data
//import dodoz.cs.rmutt.canteenwallet.model.LoginResponse
//import dodoz.cs.rmutt.canteenwallet.model.getData
//import dodoz.cs.rmutt.canteenwallet.model.user
//import kotlinx.android.synthetic.main.activity_report.*
//import okhttp3.OkHttpClient
//import retrofit2.*
//import retrofit2.converter.gson.GsonConverterFactory
//
//class ReportActivity : AppCompatActivity() {
////    private var heroList2: MutableList<test1>? = ArrayList()
////    private var adapter: ReportAdapter? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_report)
//
//
//        ref = FirebaseDatabase.getInstance().getReference(Common.PLACE_PPOSTTRUCK)
//        val query = ref.orderByChild("uniitruckKey").equalTo(userId)
//        report_requestlist!!.setHasFixedSize(true)
//        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        report_requestlist!!.layoutManager = linearLayoutManager
//
//        query.addValueEventListener(object : Callback<getData> {
//
//            override fun onFailure(call: Call<getData>, t: Throwable) {
//                TODO("Not yet implemented")
//
//
//            }
//
//            override fun onResponse(p0: Call<getData>, response: Response<getData>) {
//                heroList2?.clear()
////                heroList?.clear()
//
//                if (!response.body()?.error!!) {
//                    for (h in p) {
//                        val hero = h.getValue(test1::class.java)
//                        heroList2!!.add(hero!!)
////                        Log.d("pokpokpok", heroList!!.size.toString())
////                        Log.d("pokpokpok2", displayList!!.size.toString())
//                    }
//                    heroList2!!.reverse()
//                    adapter = PostPshowutAdapter(heroList2 as ArrayList<test1>, this@ReportActivity)
//                    report_requestlist?.adapter = adapter!!
//
//                }
//            }
//
//        });
//
////    val retrofit = Retrofit.Builder()
////        .baseUrl("http://localhost/api.php")
////        .addConverterFactory(GsonConverterFactory.create())
////        .client(okHttpClient)
////        .build()
////
////    val api = retrofit.create(Apiservice::class.java)
////
////    api.fetchAllUsers().enqueue(object : Callback<List<user>>{
////        override fun onResponse(call: Call<List<user>>, response: Response<List<user>>) {
////            d("test","onResponse : ${response.body()!![0].amount}")
////        }
////
////        override fun onFailure(call: Call<List<user>>, t: Throwable) {
////            d("test","onResponse : $")
////        }
////
////    })
////
////    val users = mutableListOf<user>()
////    for (i in 0..100){
////        users.add(user("1234","pay","20.0"))
////    }
//    }
//
////    private val okHttpClient = OkHttpClient.Builder()
////        .addInterceptor { chain ->
////            val original = chain.request()
////
////            val requestBuilder = original.newBuilder()
////                .addHeader("Authorization", AUTH)
////                .method(original.method(), original.body())
////
////            val request = requestBuilder.build()
////            chain.proceed(request)
////        }.build()
////
////    private val AUTH = "Basic "+ Base64.encodeToString(
////        "dodozkung:123456".toByteArray(),
////        Base64.NO_WRAP
////    )
//
//    private fun Report(){
//
//        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
//        val walletid = sharedPrefManager.getString("wallet_id", "")
//
//        RetrofitClient.instance.Report(walletid!!)
//            .enqueue(object: Callback<user> {
//                override fun onFailure(call: Call<user>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<user>, response: Response<user>) {
//                    d("test","onResponse : ${response.body()!![0].amount}" )
//
//
//                }
//            })
//    }
//}