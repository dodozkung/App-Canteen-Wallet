package dodoz.cs.rmutt.canteenwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.model.DefaultResponse
import dodoz.cs.rmutt.canteenwallet.model.checkidcard
import dodoz.cs.rmutt.canteenwallet.model.repassword
import kotlinx.android.synthetic.main.activity_fotgetpassword.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_repassword.*
import kotlinx.android.synthetic.main.confirm_status.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repassword)

        btnconrepassword.setOnClickListener {

            val password = fgpassword.text.toString().trim()
            val passwordcon = fgcpassword.text.toString().trim()

            if (password.isEmpty() || password.length < 6) {
                edtpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"
                return@setOnClickListener
            }
            if (passwordcon.isEmpty() || passwordcon.length < 6) {
                edtconpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"

                return@setOnClickListener
            }
            if (!passwordcon.equals(password)) {
                edtconpass.error = "รหัสผ่านไม่ตรงกัน"

                return@setOnClickListener
            }else {
                confirmDialog()
            }


        }
    }

    private fun confirmDialog() {
        val inflater = LayoutInflater.from(this)
        val subView = inflater.inflate(R.layout.confirm_exit, null)

        val ndely = subView.findViewById<Button>(R.id.btnexit)
        val ndeln = subView.findViewById<Button>(R.id.btnnoexit)

        subView.textDialog.text = "ยืนยันการสมัครสมาชิก"

        val builder = AlertDialog.Builder(this!!)
        builder.setView(subView)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        ndely.setOnClickListener {
            dialog.dismiss()

            val password = fgpassword.text.toString().trim()
            val idcard = intent.getStringExtra("idcard")


            RetrofitClient.instance.repassword(password,idcard)
                .enqueue(object : Callback<repassword> {
                    override fun onFailure(call: Call<repassword>, t: Throwable,) {
                        Toast.makeText(applicationContext, "ไม่สามารถเชื่อมต่อเซิฟเวอร์ได้", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<repassword>,
                        response: Response<repassword>
                    ) {
                        if (!response.body()?.error!!) {


                            var intent = Intent(applicationContext, RepasswordActivity::class.java)
                            startActivity(intent)
                            finish()
                            Toast.makeText(
                                applicationContext,
                                "เปลี่ยนรหัสผ่านเรียบร้อยแล้ว",
//                            response.body()?.message,
                                Toast.LENGTH_LONG
                            ).show()

                        }else{
                            Toast.makeText(
                                applicationContext,
                                "ไม่สามารถเปลี่ยนรหัสผ่านได้",
//                            response.body()?.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                })


        }
        ndeln.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()

    }
}