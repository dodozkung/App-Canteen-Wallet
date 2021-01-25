package dodoz.cs.rmutt.canteenwallet.Transfer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.MainActivity
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.model.getSearch
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.activity_transfer_confirm.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)



        cftranfer!!.setOnClickListener {
            val walletid = masked_edit_text!!.text.toString()
            val amout = Amout!!.text.toString()

            val intent = Intent(this, TransferConfirmActivity::class.java)
            intent.putExtra("walletid", walletid)
            intent.putExtra("amout", amout)
            init()

            startActivity(intent)


        }

        cctranfer!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun init(){

        val walletid = masked_edit_text!!.text.toString()

        RetrofitClient.instance.SeachUser(walletid.toInt())
            .enqueue(object : Callback<getSearch> {
                override fun onFailure(call: Call<getSearch>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<getSearch>, response: Response<getSearch>) {
                    if (!response.body()?.error!!) {

                        SharedPrefManager.getInstance(applicationContext).getSearch(response.body()?.user!!)



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

