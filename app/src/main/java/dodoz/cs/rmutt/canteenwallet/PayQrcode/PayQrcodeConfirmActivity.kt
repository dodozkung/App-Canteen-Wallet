package dodoz.cs.rmutt.canteenwallet.PayQrcode

import android.content.Intent
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.PinQRActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_pay_qrcode_confirm.*

class PayQrcodeConfirmActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_qrcode_confirm)

//        init()
//        recheck()

        val accshop = intent.getStringExtra("accshop")
        val nameshop = intent.getStringExtra("nameshop")

        showaccshop!!.setText(accshop)
        shownameshop!!.setText(nameshop)

//        Toast.makeText(this, walletid , Toast.LENGTH_LONG).show()
        val amoutshop = intent.getStringExtra("amoutshop")
        showamout!!.setText(amoutshop)
//        Toast.makeText(this, amout , Toast.LENGTH_LONG).show()

        backpayqr!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeCheckActivity::class.java)
            startActivity(intent)
        }

        checkpayqr!!.setOnClickListener {

            val intent = Intent(this, PinQRActivity::class.java)
            intent.putExtra("walletid2", accshop)
            intent.putExtra("amout", amoutshop)
            startActivity(intent)

        }

    }

//    private fun init(){
//
//        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
//
//        val name1 = sharedPrefManager.getString("name1", "")
//
////        Toast.makeText(this, name1, Toast.LENGTH_LONG).show()
//        shownameshop!!.setText(name1)
//
//    }
//
//    private fun recheck(){
//        Handler().postDelayed({
//            init()
//        },300)
//    }


}