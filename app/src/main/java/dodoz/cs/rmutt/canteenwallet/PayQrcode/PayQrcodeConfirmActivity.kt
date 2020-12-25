package dodoz.cs.rmutt.canteenwallet.PayQrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.PinActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.*
import kotlinx.android.synthetic.main.activity_pay_qrcode_confirm.*

class PayQrcodeConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qrcode_confirm)

        backtransfer!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeCheckActivity::class.java)
            startActivity(intent)
        }
        checktransfer!!.setOnClickListener {
            val intent = Intent(this, PinActivity::class.java)
            startActivity(intent)
        }

    }


}