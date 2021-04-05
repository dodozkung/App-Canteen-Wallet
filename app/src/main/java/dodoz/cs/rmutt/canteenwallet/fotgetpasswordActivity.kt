package dodoz.cs.rmutt.canteenwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.model.DefaultResponse
import dodoz.cs.rmutt.canteenwallet.model.checkidcard
import kotlinx.android.synthetic.main.activity_fotgetpassword.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtname
import kotlinx.android.synthetic.main.activity_repassword.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_fotgetpassword.toolbar

class fotgetpasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotgetpassword)

        toolbar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        btncheckidcard.setOnClickListener {

        val idcard = fgidcard.text.toString().trim()

            if (idcard.isEmpty()) {
                fgidcard.error = "โปรดกรอกเลขบัตรประชาชน"
                return@setOnClickListener
            }else if (idcard.length != 13){
                fgidcard.error = "โปรดกรอกเลขบัตรประชาชนให้ครบ 13 หลัก"
                return@setOnClickListener
            }else{

        RetrofitClient.instance.checkidcard(idcard)
            .enqueue(object : Callback<checkidcard> {
                override fun onFailure(call: Call<checkidcard>, t: Throwable,) {
                    Toast.makeText(applicationContext, "ไม่สามารถเชื่อมต่อเซิฟเวอร์ได้", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<checkidcard>,
                    response: Response<checkidcard>
                ) {
                    if (!response.body()?.error!!) {


                        var intent = Intent(applicationContext, RepasswordActivity::class.java)
                        intent.putExtra("idcard", idcard)
                        startActivity(intent)
                        finish()

                    }else{
                        Toast.makeText(
                            applicationContext,
                            "ไม่มีเลขบัตรประชาชนนี้อยู่ในระบบ",
//                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }) }

        }
    }
}