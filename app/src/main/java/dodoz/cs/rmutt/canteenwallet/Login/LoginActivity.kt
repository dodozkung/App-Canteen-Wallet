package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.MainActivity
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.Retrofit.INodeJS
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtpass
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : BaseActivity() {

    lateinit var myAPI: INodeJS
    var compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Init API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)
        var token = getSharedPreferences("salt",Context.MODE_PRIVATE)



        btnlogin.setOnClickListener {
            val username = edtid.text.toString().trim()
            val password = edtpass.text.toString().trim()


            if (username.isEmpty()) {
                edtid.error = "กรุณากรอกชื่อผู้ใช้งาน"

                return@setOnClickListener
            }else if (password.isEmpty()) {
                edtpass.error = "กรุณากรอกรหัสผ่าน"

                return@setOnClickListener
            }else
            login(edtid.text.toString(),edtpass.text.toString())

        }

        btnregis.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }


    }


    private fun login (username: String,password:String){
        compositeDisposable.add(myAPI.login(username,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { message ->
                if(message.contains("encrypted_password")) {
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    },2000)
                }else
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
            })


    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
