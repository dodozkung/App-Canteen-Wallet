package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnconotp!!.setOnClickListener(View.OnClickListener {
//            showDialog()
            val phone = phonenumber!!.text.toString()
            if (phone.isEmpty() || phone.length < 10) {
                if (!phone.startsWith("0") && phone.length == 9) {
//                    hideDialog()
                    val intent = Intent(this, VerifyActivity::class.java)
                    intent.putExtra("Phone", "0$phone")
                    startActivity(intent)
                } else {
//                    hideDialog()
                    phonenumber!!.error = "เบอร์โทรศัพท์ไม่ถูกต้องกรุณาลองอีกครั้ง"
                    phonenumber!!.requestFocus()
                    return@OnClickListener
                }
            } else {
                if (!phone.startsWith("0") && phone.length == 9) {
//                    hideDialog()
                    val intent = Intent(this, VerifyActivity::class.java)
                    intent.putExtra("Phone", phone)
                    startActivity(intent)
                } else {
//                    hideDialog()
                    phonenumber!!.error = "เบอร์โทรศัพท์ไม่ถูกต้องกรุณาลองอีกครั้ง"
                    phonenumber!!.requestFocus()
                    return@OnClickListener
                }
            }
        })


    }
    }
