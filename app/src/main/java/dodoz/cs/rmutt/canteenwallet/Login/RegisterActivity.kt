package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

import dodoz.cs.rmutt.canteenwallet.*
import dodoz.cs.rmutt.canteenwallet.Retrofit.INodeJS
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtpass
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

//        init()

        //Init API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)


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

        btnconprofile.setOnClickListener {
            val name = edtname.text.toString().trim()
            val idcard = edtidcard.text.toString().trim()
            val username = edtusername.text.toString().trim()
            val phone = edtphone.text.toString().trim()
            val address = edtaddress.text.toString().trim()
            val password = edtpass.text.toString().trim()
            val conpassword = edtconpass.text.toString().trim()
            val pw = edtpw.text.toString().trim()






            if (username.isEmpty()) {
                 edtusername.error = "กรุณากรอกชื่อผู้ใช้"
                return@setOnClickListener
            } else if (password.isEmpty() || password.length <=6) {
                edtpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"

                return@setOnClickListener
            } else if (conpassword.isEmpty() || conpassword.length <=6) {
                edtconpass.error = "กรุณากรอกรหัสผ่านมากกว่า 5 ตัว"

                return@setOnClickListener
            } else if (!conpassword.equals(password)) {
                edtconpass.error = "รหัสผ่านไม่ตรงกัน"

                return@setOnClickListener
            }  else if (name.isEmpty()) {
                edtname.error = "กรุณากรอกชื่อ - นามสกุล"
                return@setOnClickListener
            }  else if (address.isEmpty()) {
                edtaddress.error = "กรุณากรอกรหัสนักศึกษา"

                return@setOnClickListener
            }   else if (idcard.isEmpty()) {
                edtidcard.error = "กรุณากรอกรหัสนักศึกษา"

                return@setOnClickListener
            }       else if (pw.isEmpty() || pw.length == 6) {
                edtpw.error = "กรุณากรอกรหัสธุรกรรม 6 ตัว"

                return@setOnClickListener
            }   else if (phone.isEmpty() || phone.length <9) {
                edtphone.error = "กรุณากรอกเบอร์โทรศัพท์"

                return@setOnClickListener
            }   else {
                register(edtusername.text.toString(),
                    edtpass.text.toString(),
                    edtname.text.toString(),
                    edtaddress.text.toString(),
                    edtidcard.text.toString(),
                    edtpw.text.toString(),
                    edtphone.text.toString())
            }
            }


        }

    private fun register (
        username : String,
        password: String,
        name: String,
        adddress: String,
        idcard: String,
        passconfirm : String,
        phone : String
    ){
        compositeDisposable.add(myAPI.register(username,password,name,adddress,idcard,passconfirm,phone)
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