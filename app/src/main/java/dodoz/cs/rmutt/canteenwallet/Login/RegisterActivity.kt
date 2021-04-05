package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import dodoz.cs.rmutt.canteenwallet.*
import dodoz.cs.rmutt.canteenwallet.Retrofit.RetrofitClient
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.model.DefaultResponse
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtpass
import kotlinx.android.synthetic.main.confirm_status.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegisterActivity : BaseActivity() {

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
        toolbar.setOnClickListener {
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
            else if (!idcard.isEmpty() || idcard.length == 13) {
                if (isValidThaiId(idcard) == true){
                    //เช็คบัตรประชาชน
                    if (pw.isEmpty() || pw.length < 6) {
                        edtpw.error = "กรุณากรอกรหัสธุรกรรม 6 ตัว"

                        return@setOnClickListener
                    }else if (phone.isEmpty() || phone.length < 10) {
                        edtphone.error = "กรุณากรอกเบอร์โทรศัพท์ 10 หลัก"

                        return@setOnClickListener
                    }else {
                        confirmDialog()
                    }
                }else
                edtidcard.error = "บัตรประชาชนไม่ครบ13หลักหรือไม่ถูกต้อง"
                return@setOnClickListener

            }

//            else if (!IDCardFormat.isValidThaiId(idcard) &&  idcard != "") {
//                edtidcard!!.error = "รหัสบัตรประชาชนผิดพลาด โปรดลองอีกครั้ง"
////                edtidcard!!.requestFocus()
//                return@setOnClickListener
//            }

//            else if (pw.isEmpty() || pw.length < 6) {
//                edtpw.error = "กรุณากรอกรหัสธุรกรรม 6 ตัว"
//
//                return@setOnClickListener
//            }else if (phone.isEmpty() || phone.length < 10) {
//                edtphone.error = "กรุณากรอกเบอร์โทรศัพท์ 10 หลัก"
//
//                return@setOnClickListener
//            }else {
//                confirmDialog()
//            }




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

//    private fun isValidThaiId(): Boolean {
//        val idcard = edtidcard.text.toString().trim()
//        this.let {
//            return if (idcard.length != 13) {
//                false
//            } else {
//                val digits = ("0${idcard.toInt()}").substring(0, 13).map { idcard.toInt() }
//                val lastDigit = idcard.substring(12, 13)
//                val sum = digits.reduceIndexed { index, total, next ->
//                    total + (next * (13 + 1 - index))
//                }
//                val checkDigit = 11 - (sum % 11)
//
//                (checkDigit.toString()[0].toString() == lastDigit)
//
//            }
//        }
//    }


    private fun isValidThaiId(idcard : String): Boolean {
        //val testnumber = "1179900359710" //
        this.let {
            return if (idcard.length != 13 || idcard.isEmpty()) {
                false
            } else {
                val list = mutableListOf<Int>()
                val index = 13
                val digits = idcard.substring(0, 12)
                val lastDigit = idcard.substring(12, 13)
                val numbers = digits.map { it.toString().toInt() }
                //list.addAll(listOf(1, 2, 3))
                //println(list) // [1, 2, 3]
                for (i in numbers.indices) {
                    val sumtest = (numbers[i] * (index - i))
                    list.add(sumtest)
                }
                val finaldigit = (11 - ((list.sumOf { it }) % 11)) % 10

                if (lastDigit.toInt() != finaldigit || idcard.length != 13) {
                    return false
                } else {
                    return true
                }
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

            val name = edtname.text.toString().trim()
            val idcard = edtidcard.text.toString().trim()
            val username = edtusername.text.toString().trim()
            val phone = edtphone.text.toString().trim()
            val address = edtaddress.text.toString().trim()
            val password = edtpass.text.toString().trim()
            val pw = edtpw.text.toString().trim()

            RetrofitClient.instance.createuser(username, password, name, address, idcard,pw, phone)
                .enqueue(object : Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable,) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
//                        Toast.makeText(
//                            applicationContext,
//                            response.body()?.message,
//                            Toast.LENGTH_LONG
//                        ).show()
                        if (!response.body()?.error!!) {

//                            Toast.makeText(
//                            applicationContext,
//                            response.body()?.message,
//                            Toast.LENGTH_LONG
//                        ).show()
                            Toast.makeText(
                                applicationContext,
                                "สมัครสมาชิกสำเร็จ",
                                Toast.LENGTH_LONG
                            ).show()

                            var intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                            finish()



                        }else if (response.body()?.error!!) {
                            Toast.makeText(
                                applicationContext,
                                "ชื่อผู้ใช้มีอยู่แล้วกรุณาตั้งชื่อผู้ใช้ใหม่",
//                            response.body()?.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                })


        }
        ndeln.setOnClickListener {
            dialog.cancel()
//            hideDialog()
        }
        dialog.show()

    }

//    private fun valid_citizen_id(): Boolean {
//        if (edtidcard.length() != 13) {
//            return false
//        }
//        val rev = edtidcard.text.reversed()
//        val total = 0
//        for (i in 1..13) {
//            val mul = i + 1
//            val count = rev[i].toInt()*mul
//                val total = total + count
//        }
//        val mod = total % 11
//        val sub = 11 - mod
//        val check_digit = sub % 10
//        if (rev[0] == check_digit) {
//            return true
//        } else {
//            return false
//        }
//
//    }




}