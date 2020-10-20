package dodoz.cs.rmutt.centeenwallet

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_verify.*

class VerifyActivity : BaseActivity() {

    private var timerstate = false
    private var time: Long = 60000
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        reotp!!.setOnClickListener {
//            if (mobile != null) {
//                resendVerificationCode(mobile, mResendToken)
//            }
            time = 10000
            timerstate = false
            reotp.isEnabled = false
            timestart()

            time_countdown.visibility = View.VISIBLE
            reotp.background = getCompatDrawable(R.drawable.wait_otp)
        }

        btnconfirmotp!!.setOnClickListener(View.OnClickListener {
//            showDialog()
            val code = otpconfirm!!.text.toString().trim()
//            if (code.isEmpty() || code.length < 6) {
//                hideDialog()
//                otpconfirm!!.error = "รหัส OTP ผิดกรุณาลองอีกครั้ง"
//                otpconfirm!!.requestFocus()
//                return@OnClickListener
//            } else {
//                verifyPhoneNumberWithCode(mVerificationId, code)
//            }

            if (code.isEmpty() || code.length<6 || code != "111111"){
                otpconfirm!!.error = "รหัส OTP ไม่ถูกต้องกรุณาลองใหม่อีกครั้ง"
            }else {
//                hideDialog()
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

        })

    }

    private fun getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(this, drawableRes)
    }

    private fun timestart() {
        timer = object : CountDownTimer(time, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                time = millisUntilFinished
                time_countdown.text =
                    "เหลือเวลาอีก " + millisUntilFinished / 1000 + " วินาที เพื่อขอรหัส OTP อีกครั้ง..."
            }

            override fun onFinish() {
                if (!timerstate) {
                    timerstate = true
                    reotp.isEnabled = true
                    time_countdown.visibility = View.GONE
                    time = 10000
                    reotp.background = getCompatDrawable(R.drawable.btnotp)
                }
            }
        }.start()
    }

}