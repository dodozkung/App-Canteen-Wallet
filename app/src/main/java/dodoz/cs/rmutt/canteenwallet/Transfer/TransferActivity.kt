package dodoz.cs.rmutt.canteenwallet.Transfer

import android.content.Intent
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.MainActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_transfer.*

class TransferActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        cctranfer!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cftranfer!!.setOnClickListener {
            val intent = Intent(this, TransferConfirmActivity::class.java)
            startActivity(intent)
        }
    }
}