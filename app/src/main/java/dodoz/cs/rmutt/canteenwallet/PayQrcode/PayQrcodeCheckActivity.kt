package dodoz.cs.rmutt.canteenwallet.PayQrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.PinActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_pay_qrcode_check.*
import kotlinx.android.synthetic.main.activity_transfer_confirm.*

class PayQrcodeCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qrcode_check)

        cctranfer!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeActivity::class.java)
            startActivity(intent)
        }
        cftranfer!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeConfirmActivity::class.java)
            startActivity(intent)
        }

    }

}