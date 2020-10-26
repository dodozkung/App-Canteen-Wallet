package dodoz.cs.rmutt.canteenwallet.Transfer

import android.content.Intent
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_transfer_confirm.*

class TransferConfirmActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_confirm)

        backtransfer!!.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

//        checktransfer!!.setOnClickListener {
//            val intent = Intent(this,)
//        }
    }
}