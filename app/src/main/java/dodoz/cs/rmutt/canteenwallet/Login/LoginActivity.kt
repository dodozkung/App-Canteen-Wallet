package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import dodoz.cs.rmutt.canteenwallet.*
import dodoz.cs.rmutt.canteenwallet.Retrofit.Api
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.model.LoginResponse
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtpass
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        forgetpassword.setOnClickListener {
            val intent = Intent(this, fotgetpasswordActivity::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val username = edtid.text.toString().trim()
            val password = edtpass.text.toString().trim()


            if (username.isEmpty()) {
                edtid.error = "กรุณากรอกชื่อผู้ใช้งาน"

                return@setOnClickListener
            }
            if (password.isEmpty()) {
                edtpass.error = "กรุณากรอกรหัสผ่าน"

                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(username, password)
                .enqueue(object: Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, "ไม่สามารถเชื่อมต่อเซิฟเวอร์ได้", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(!response.body()?.error!!){

                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)
//
                            val checkstatus = response.body()?.user!!.status
                            if (checkstatus == "user") {
                                    val intent = Intent(applicationContext, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                    startActivity(intent)

                                } else if (checkstatus == "shop"){
                                    val intent = Intent(applicationContext, MainShopActivity::class.java)
                                    intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                    startActivity(intent)

                            } else {
                                Toast.makeText(applicationContext, "ชื่อผู้ใช้นี้ไม่สามารถใช้งานแอปพลิเคชันได้", Toast.LENGTH_LONG).show()
                            }
                    }else {
                            Toast.makeText(applicationContext, "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }


        btnregis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }




//    private fun login(username: String, password: String) {
//        compositeDisposable.add(myAPI.login(username, password)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { message ->
//                var txt = message.split(',').toTypedArray()
//                var user = txt[2]
//                var walletid = txt[0].substring(1,txt[0].length)
//                var balance = txt[1]
//                var pwcf = txt[3].substring(0,txt[3].length-1)
//
//                if (!message.contains("Wrong password")) {
//                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
//                    Handler().postDelayed({
//                        session.setUser(user)
//                        session.setBalance(balance)
//                        session.setWallet(walletid)
//                        session.setPassconfirm(pwcf)
//                        Toast.makeText (this, user, Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this, walletid, Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this, balance, Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this, pwcf, Toast.LENGTH_SHORT).show()
//                        intent.putExtra("username", user)
//
//
//                        startActivity(Intent(this, MainActivity::class.java))
//                        finish()
//                    }, 2000)
//                } else
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//            })
//
//
//    }

//    override fun onStop() {
//        compositeDisposable.clear()
//        super.onStop()
//    }
//
//    override fun onDestroy() {
//        compositeDisposable.clear()
//        super.onDestroy()
//    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

}
