package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import dodoz.cs.rmutt.canteenwallet.PayQrcode.PayQrcodeActivity
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

            logopin.setOnClickListener {
                val code = firstPinView!!.text.toString().trim()
                if (code.isEmpty() || code.length < 6) {
//                hideDialog()
                    firstPinView!!.error = "รหัส OTP ผิดกรุณาลองอีกครั้ง"
                    firstPinView!!.requestFocus()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }








    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}


