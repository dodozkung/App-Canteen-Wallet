package dodoz.cs.rmutt.canteenwallet.Transfer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.PinActivity
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.model.getData
import dodoz.cs.rmutt.canteenwallet.model.getSearch
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_transfer_confirm.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferConfirmActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_confirm)

        init()




        val walletid = intent.getStringExtra("walletid")
        walletidcf!!.setText(walletid)
//        Toast.makeText(this, walletid , Toast.LENGTH_LONG).show()
        val amout = intent.getStringExtra("amout")
        amoutcf!!.setText(amout)
//        Toast.makeText(this, amout , Toast.LENGTH_LONG).show()

        backtransfer!!.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        checktransfer!!.setOnClickListener {
            val intent = Intent(this,PinActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init(){

        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val name1 = sharedPrefManager.getString("name1", "")

        Toast.makeText(this, name1, Toast.LENGTH_LONG).show()
        showname!!.setText(name1)


        val walletid = intent.getStringExtra("walletid")
//        Toast.makeText(this, walletid , Toast.LENGTH_LONG).show()

        RetrofitClient.instance.SeachUser(walletid.toInt())
            .enqueue(object : Callback<getSearch> {
                override fun onFailure(call: Call<getSearch>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<getSearch>, response: Response<getSearch>) {
                    if (!response.body()?.error!!) {

                        SharedPrefManager.getInstance(this@TransferConfirmActivity).getSearch(response.body()?.user!!)



                    } else {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            })

    }

}