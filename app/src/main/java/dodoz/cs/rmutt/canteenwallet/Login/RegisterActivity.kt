package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

import dodoz.cs.rmutt.canteenwallet.*
import dodoz.cs.rmutt.canteenwallet.Retrofit.Api
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.model.DefaultResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtpass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegisterActivity : BaseActivity() {

    private var dateTime = ""
    lateinit var myAPI: Api
    var compositeDisposable = CompositeDisposable()


    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)

    var getSex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        init()

        //Init API


//        rg_gender.setOnCheckedChangeListener { group, checkedId ->
//            when (checkedId) {
//                R.id.rb_male -> {
//                    getSex = getString(R.string.male)
//                }
//                R.id.rb_female -> {
//                    getSex = getString(R.string.female)
//                }
//            }
//        }

        btnreturnlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnconprofile.setOnClickListener {
            val name = edtname.text.toString().trim()
            val idcard = edtidcard.text.toString().trim()
            val username = edtusername.text.toString().trim()
            val phone = edtphone.text.toString().trim()
            val address = edtaddress.text.toString().trim()
            val password = edtpass.text.toString().trim()
            val passconfirm = edtconpass.text.toString().trim()
            val pw = edtpw.text.toString().trim()






            if (username.isEmpty()) {
                edtusername.error = "กรุณากรอกชื่อผู้ใช้"
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                edtpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"
                return@setOnClickListener
            }
            if (passconfirm.isEmpty() || passconfirm.length < 6) {
                edtconpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"

                return@setOnClickListener
            }
            if (!passconfirm.equals(password)) {
                edtconpass.error = "รหัสผ่านไม่ตรงกัน"

                return@setOnClickListener
            }
            if (name.isEmpty()) {
                edtname.error = "กรุณากรอกชื่อ - นามสกุล"
                return@setOnClickListener
            }
            if (address.isEmpty()) {
                edtaddress.error = "กรุณากรอกที่อยู่"

                return@setOnClickListener
            }
            if (idcard.isEmpty()) {
                edtidcard.error = "กรุณากรอกรหัสนักศึกษา"

                return@setOnClickListener
            } else if (pw.isEmpty() || pw.length < 6) {
                edtpw.error = "กรุณากรอกรหัสธุรกรรม 6 ตัว"

                return@setOnClickListener
            }
            if (phone.isEmpty() || phone.length < 10) {
                edtphone.error = "กรุณากรอกเบอร์โทรศัพท์ 10 หลัก"

                return@setOnClickListener
            }

            RetrofitClient.instance.createuser(
                username,
                password,
                name,
                address,
                idcard,
                passconfirm,
                phone
            )
                .enqueue(object : Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })


        }


    }

    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }


//    private fun init() {
////        loadPhoneNumber()
//        birthday!!.setOnClickListener {
//            val mDateSetListener: DatePickerDialog.OnDateSetListener =
//                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                    myCalendar.set(Calendar.YEAR, year)
//                    myCalendar.set(Calendar.MONTH, monthOfYear)
//                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                    updateLabel()
//                }
//            val d = DatePickerDialog(
//                this,
//                R.style.DialogTheme,
//                mDateSetListener,
//                myCalendar.get(Calendar.YEAR),
//                myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)
//            )
//            d.show()
//        }
//
//        AndroidThreeTen.init(this)
//    }
//
//
//
//    private fun updateLabel() {
//        val myFormat = "yyyy-MM-dd"
//        val sdf = SimpleDateFormat(myFormat, Locale.US)
//        val myFormatshow = "dd/MM/yyyy"
//        val sdfShow = SimpleDateFormat(myFormatshow, Locale.US)
//        dateTime = sdf.format(myCalendar.time)
//
//        birthday!!.setText(sdfShow.format(myCalendar.time))
//
//    }


}