package dodoz.cs.rmutt.canteenwallet.PayQrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.PinActivity
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.Transfer.TransferConfirmActivity
import dodoz.cs.rmutt.canteenwallet.model.getSearch
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.*
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.cctranfer
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.cftranfer
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.activity_transfer_confirm.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayQrcodeCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qrcode_check)

        val qrshop = intent.getStringExtra("qraccshop")
        accshop!!.setText(qrshop)
//        Toast.makeText(this, qrshop , Toast.LENGTH_LONG).show()



        cctranfer!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeActivity::class.java)
            startActivity(intent)
        }
        cftranfer!!.setOnClickListener {

            val amout = amoutshop!!.text.toString()

            val intent = Intent(this, PayQrcodeConfirmActivity::class.java)
            intent.putExtra("accshop", qrshop)
            intent.putExtra("amoutshop", amout)
            init()

            startActivity(intent)
            finish()

        }

    }

    private fun init(){

        val accshop = accshop!!.text.toString()

        RetrofitClient.instance.SeachUser(accshop.toInt())
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