package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.chaos.view.PinView
import com.goodiebag.pinview.Pinview
import dodoz.cs.rmutt.canteenwallet.PayQrcode.PayQrcodeActivity
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.model.*
import kotlinx.android.synthetic.main.activity_pin.*
import kotlinx.android.synthetic.main.activity_transfer_confirm.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

//        init()

//        val EndAcc = intent.getStringExtra("walletid2")
//        Toast.makeText(this, EndAcc , Toast.LENGTH_LONG).show()
        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val walletid = sharedPrefManager.getString("wallet_id", "")
        val status =  sharedPrefManager.getString("passconfirm", "")
        val EndAcc = intent.getStringExtra("walletid2")
        val amout = intent.getStringExtra("amout")

//                val code = firstPinView!!.text.toString().trim()
//                if (code.isEmpty() || code.length < 6) {
////                hideDialog()
//                    firstPinView!!.error = "รหัส OTP ผิดกรุณาลองอีกครั้ง"
//                    firstPinView!!.requestFocus()
//                } else if (code.equals("123456")) {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                }

        val pinview1 = findViewById<Pinview>(R.id.pinview1)

        pinview1.setPinViewEventListener { pinview, fromUser ->
//            Toast.makeText(
//                this,
//                pinview.value,
//                Toast.LENGTH_SHORT
//            ).show()

            if (pinview1.value == status) {

                RetrofitClient.instance.postTransferT(walletid!!,EndAcc,amout.toFloat())
                    .enqueue(object : Callback<Transfer> {
                        override fun onFailure(call: Call<Transfer>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<Transfer>, response: Response<Transfer>) {
                            if (response.body()?.user!!) {

                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)

                                Toast.makeText(
                                    applicationContext,
                                    "ทำรายการสำเร็จ",
                                    Toast.LENGTH_LONG
                                ).show()

//                                SharedPrefManager.getInstance(applicationContext).getSearch(response.body()?.user!!)

//                                TransferTr()





                            } else {
//                                Toast.makeText(
//                                    applicationContext,
//                                    response.body()?.message,
//                                    Toast.LENGTH_LONG
//                                ).show()
                                Toast.makeText(
                                    applicationContext,
                                    "ไม่สามารถทำรายการได้",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
                    })



            } else {
                Toast.makeText(
                    this,
                    "รหัสไม่ถูกต้องกรุณาลองใหม่",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }


    }

//    private fun init(){
//
//        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
//
//        val walletid = sharedPrefManager.getString("wallet_id", "")
//        val passconfirm =  sharedPrefManager.getString("passconfirm", "")
//        val EndAcc = intent.getStringExtra("walletid2")
//        val amout = intent.getStringExtra("amout")

//        Toast.makeText(this, walletid +"-"+ passconfirm + "-" + amout + "-" + EndAcc , Toast.LENGTH_LONG).show()
//    }

//    private fun TransferTr(){
//
//        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
//
//        val wallet_id = sharedPrefManager.getInt("wallet_id", 0)
//        val EndAccID = intent.getStringExtra("walletid2")
//        val Amount = intent.getStringExtra("amout")
//
////        Toast.makeText(this, wallet_id.toString()  + "-" + Amount + "-" + EndAccID , Toast.LENGTH_LONG).show()
//
//        RetrofitClient.instance.postTransferT(wallet_id.toString(),EndAccID,Amount)
//
////            .enqueue(object : Callback<DefaultResponse> {
////                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
////                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
////                }
////
////                override fun onResponse(
////                    call: Call<DefaultResponse>,
////                    response: Response<DefaultResponse>
////                ) {
////                    Toast.makeText(
////                        applicationContext,
////                        response.body()?.message,
////                        Toast.LENGTH_LONG
////                    ).show()
////                }
////
////            })
//            .enqueue(object : Callback<TransferA> {
//                override fun onFailure(call: Call<TransferA>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<TransferA>, response: Response<TransferA>) {
//                    if (response.body()?.error!! ) {
//
//                        Toast.makeText(
//                            applicationContext,
//                            response.body()?.error!!.toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
////
//
//
//                    } else {
////                                Toast.makeText(
////                                    applicationContext,
////                                    response.body()?.message,
////                                    Toast.LENGTH_LONG
////                                ).show()
//                        Toast.makeText(
//                            applicationContext,
//                            "ไม่สามารถทำรายการได้",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//
//                }
//            })





//    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}


