package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity
import dodoz.cs.rmutt.canteenwallet.PayQrcode.PayQrcodeActivity
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.Transfer.TransferActivity
import dodoz.cs.rmutt.canteenwallet.model.getData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnlogout.setOnClickListener {

            SharedPrefManager.getInstance(this).clear()

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        init()

        Handler().postDelayed({
            init()
        },300)


        refresh.setOnClickListener {
                init()
//            Log.d("1212", "CACA")
        }



        btn_check.setOnClickListener {
            var intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
            finish()

        }

        transfermain.setOnClickListener {

            var intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
            finish()

        }

        payqrcodemain.setOnClickListener {

            var intent = Intent(this, PayQrcodeActivity::class.java)
            startActivity(intent)
            finish()

        }



    }
    override fun onStart() {
        super.onStart()

        if(!SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    private fun init(){
        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val walletid = sharedPrefManager.getString("wallet_id", "")
//        Toast.makeText(applicationContext, walletid.toString(), Toast.LENGTH_LONG).show()
        val balance = sharedPrefManager.getFloat("balance", 0.0F)
        Balance.text = balance.toString()
        val name = sharedPrefManager.getString("name", "")
        acccount.text = walletid + " - " + name.toString()


        RetrofitClient.instance.getDataUser(walletid!!)
            .enqueue(object : Callback<getData> {
                override fun onFailure(call: Call<getData>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<getData>, response: Response<getData>) {
                    if (!response.body()?.error!!) {

                        SharedPrefManager.getInstance(this@MainActivity)
                            .getUser(response.body()?.user!!)


                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            "มาจากตรงนี้สินะ",
////                            response.body()?.message,
//                            Toast.LENGTH_LONG
//                        ).show()
                    }

                }
            })
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "กรุณากดอีกครั้งเพื่อออกแอปพลิเคชั่น", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}

