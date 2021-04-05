package dodoz.cs.rmutt.canteenwallet.PayQrcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.*
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.cctranfer
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.cftranfer
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.activity_transfer_confirm.*

class PayQrcodeCheckActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qrcode_check)

        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val money = sharedPrefManager.getFloat("balance", 0.0f)
        val nameshop = sharedPrefManager.getString("name1", "")

        val qrshop = intent.getStringExtra("qraccshop")

        accshop!!.setText(nameshop)
//        Toast.makeText(this, qrshop , Toast.LENGTH_LONG).show()



        cctranfer!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeActivity::class.java)
            startActivity(intent)

        }
        cftranfer!!.setOnClickListener {

            val walletid = accshop!!.text.toString()
            val amout = amoutshop!!.text.toString()

//            if (walletid.isEmpty()) {
//                masked_edit_text.error = "กรุณากรอกบัญชี"
//                return@setOnClickListener
//            }
            if (amout.isEmpty()) {
                Amout.error = "กรุณากรอกจำนวนเงิน"
                return@setOnClickListener
            }

            if (money!!.toFloat() >= amout.toFloat()){

//                RetrofitClient.instance.SeachUser(qrshop.toInt())
//                    .enqueue(object : Callback<getSearch> {
//                        override fun onFailure(call: Call<getSearch>, t: Throwable) {
//                            //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                            Toast.makeText(
//                                applicationContext,
//                                "ไม่มีบัญชีนี้อยู่ในระบบ",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//
//                        override fun onResponse(call: Call<getSearch>, response: Response<getSearch>) {
//                            if (response.body()?.error!!) {
//
//                                SharedPrefManager.getInstance(applicationContext).getSearch(response.body()?.user!!)

                                val intent = Intent(applicationContext, PayQrcodeConfirmActivity::class.java)
                                intent.putExtra("accshop", qrshop)
                                intent.putExtra("nameshop", nameshop)
                                intent.putExtra("amoutshop", amout)
                                startActivity(intent)

                            } else {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body()?.message,
//                            Toast.LENGTH_LONG
//                        ).show()
                                Toast.makeText(
                                    applicationContext,
                                    "จำนวนเงินไม่เพียงพอ",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
//                    })

//            }else {
//                Toast.makeText(
//                    applicationContext,
//                    "จำนวนเงินไม่เพียงพอ",
//                    Toast.LENGTH_LONG
//                ).show()
//            }

//            val amout = amoutshop!!.text.toString()
//
//            val intent = Intent(this, PayQrcodeConfirmActivity::class.java)
//            intent.putExtra("accshop", qrshop)
//            intent.putExtra("amoutshop", amout)
//            init()
//
//            startActivity(intent)
//            finish()

        }

    }

//    private fun init(){
//
//        val accshop = accshop!!.text.toString()
//
//        RetrofitClient.instance.SeachUser(accshop.toInt())
//            .enqueue(object : Callback<getSearch> {
//                override fun onFailure(call: Call<getSearch>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<getSearch>, response: Response<getSearch>) {
//                    if (!response.body()?.error!!) {
//
//                        SharedPrefManager.getInstance(applicationContext).getSearch(response.body()?.user!!)
//
//
//
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body()?.message,
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//
//                }
//            })
//    }



//}