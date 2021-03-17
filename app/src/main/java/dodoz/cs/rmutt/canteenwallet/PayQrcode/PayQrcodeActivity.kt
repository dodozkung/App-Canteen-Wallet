package dodoz.cs.rmutt.canteenwallet.PayQrcode

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import dodoz.cs.rmutt.canteenwallet.MainActivity
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.Transfer.TransferConfirmActivity
import dodoz.cs.rmutt.canteenwallet.model.getSearch
import kotlinx.android.synthetic.main.activity_pay_qrcode.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayQrcodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qrcode)

//        btn_scan.setOnClickListener {
//            val scanner = IntentIntegrator(this)
//            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
//            scanner.setBeepEnabled(false)
//            scanner.initiateScan()
//        }

//        val integrator = IntentIntegrator(this).apply {
//            captureActivity = CaptureActivity::class.java
//            setOrientationLocked(false)
//            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//            setPrompt("Scanning Code")
//            }
//            integrator.initiateScan()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    val qrscan = result.contents

//                    Toast.makeText(this, "Scanned: " + qrscan, Toast.LENGTH_LONG).show()


//                    val intent = Intent(this, PayQrcodeCheckActivity::class.java)




                    RetrofitClient.instance.SeachUser(qrscan.toInt())
                        .enqueue(object : Callback<getSearch> {
                            override fun onFailure(call: Call<getSearch>, t: Throwable) {
                                //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                                Toast.makeText(
                                    applicationContext,
                                    "ไม่มีบัญชีนี้อยู่ในระบบ",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            override fun onResponse(call: Call<getSearch>, response: Response<getSearch>) {
                                if (response.body()?.error!!) {

                                    SharedPrefManager.getInstance(applicationContext).getSearch(response.body()?.user!!)

                                    val intent = Intent(applicationContext, PayQrcodeCheckActivity::class.java)
                                    intent.putExtra("qraccshop", qrscan)
                                    startActivity(intent)
                                    finish()

                                } else {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body()?.message,
//                            Toast.LENGTH_LONG
//                        ).show()
                                    Toast.makeText(
                                        applicationContext,
                                        "ไม่เจอข้อมูล",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                            }
                        })

                }






            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val integrator = IntentIntegrator(this).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("Scanning Code")
            }
            integrator.initiateScan()
    }



    }
