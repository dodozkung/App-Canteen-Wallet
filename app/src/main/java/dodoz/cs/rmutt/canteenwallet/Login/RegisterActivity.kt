package dodoz.cs.rmutt.canteenwallet.Login

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast

import com.jakewharton.threetenabp.AndroidThreeTen
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dodoz.cs.rmutt.canteenwallet.*
import dodoz.cs.rmutt.canteenwallet.Retrofit.INodeJS
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtpass
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : BaseActivity() {

    private var dateTime = ""
    lateinit var myAPI: INodeJS
    var compositeDisposable = CompositeDisposable()



    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)

    var getSex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        //Init API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)


        rg_gender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_male -> {
                    getSex = getString(R.string.male)
                }
                R.id.rb_female -> {
                    getSex = getString(R.string.female)
                }
            }
        }

        btnconprofile.setOnClickListener {
//            val name = edtname.text.toString().trim()
//            val idcard = edtidcard.text.toString().trim()
//            val email = edtemail.text.toString().trim()
//            val phone = edtphone.text.toString().trim()
//            val birthday = birthday.text.toString().trim()
////            val money = "0"
//            val password = edtpass.text.toString().trim()
//            val conpassword = edtconpass.text.toString().trim()
//            val pw = edtpw.text.toString().trim()

            register(edtemail.text.toString(),edtname.text.toString(),edtpass.text.toString())

//            if (getSex == null) {
//                Toast.makeText(this, "กรุณาระบุเพศ", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            } else if (idcard.isEmpty()) {
//                edtidcard.error = "กรุณากรอกรหัสนักศึกษา"
//
//                return@setOnClickListener
//            } else if (name.isEmpty()) {
//                edtname.error = "กรุณากรอกชื่อ - นามสกุล"
//                Log.d("AAAA", "0")
//                return@setOnClickListener
//            } else if (birthday.isEmpty()) {
//                Toast.makeText(this, "กรุณาระบุวันเกิด", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            } else if (phone.isEmpty() || phone.length < 6) {
//                edtphone.error = "กรุณากรอกเบอร์โทรศัพท์"
//
//                return@setOnClickListener
//            }  else if (email.isEmpty()) {
//                edtemail.error = "กรุณากรอกอีเมล"
//
//                return@setOnClickListener
//            } else if (password.isEmpty() || password.length <=6) {
//                edtpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"
//
//                return@setOnClickListener
//            } else if (conpassword.isEmpty() || conpassword.length <=6) {
//                edtconpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"
//
//                return@setOnClickListener
//            } else if (!conpassword.equals(password)) {
//                edtconpass.error = "รหัสผ่านไม่ตรงกัน"
//
//                return@setOnClickListener
//            } else if (pw.isEmpty() || pw.length == 6) {
//                edtpw.error = "กรุณากรอกรหัสธุรกรรม 6 ตัว"
//
//                return@setOnClickListener
//            } else {
//                val intent = Intent(this,LoginActivity::class.java)
//                startActivity(intent)
//            }
            }


        }

    private fun register (email: String,name: String,password:String){
        compositeDisposable.add(myAPI.register(email,name,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { message ->
                    Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    },2000)
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


    private fun init() {
//        loadPhoneNumber()
        birthday!!.setOnClickListener {
            val mDateSetListener: DatePickerDialog.OnDateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateLabel()
                }
            val d = DatePickerDialog(
                this,
                R.style.DialogTheme,
                mDateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )
            d.show()
        }

        AndroidThreeTen.init(this)
    }



    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val myFormatshow = "dd/MM/yyyy"
        val sdfShow = SimpleDateFormat(myFormatshow, Locale.US)
        dateTime = sdf.format(myCalendar.time)

        birthday!!.setText(sdfShow.format(myCalendar.time))

    }




}