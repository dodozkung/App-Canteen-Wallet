package dodoz.cs.rmutt.canteenwallet.Transfer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.PinActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_transfer_confirm.*

class TransferConfirmActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_confirm)

        init()
        recheck()
        






        val walletid = intent.getStringExtra("walletid")
        walletidcf!!.setText(walletid)
//        Toast.makeText(this, walletid , Toast.LENGTH_LONG).show()
        val amout = intent.getStringExtra("amout")
        amoutcf!!.setText(amout)
//        Toast.makeText(this, amout , Toast.LENGTH_LONG).show()

        backtransfer!!.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        checktransfer!!.setOnClickListener {
            val intent = Intent(this, PinActivity::class.java)
//            val walletid = intent.getStringExtra("walletid")

            intent.putExtra("walletid2", walletid)
            intent.putExtra("amout", amout)


            startActivity(intent)

        }
    }

    private fun init(){

        val sharedPrefManager = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)

        val name1 = sharedPrefManager.getString("name1", "")

//        if (name1 == "null"){
//            Toast.makeText(this, "หาข้อมูลไม่ได้", Toast.LENGTH_LONG).show()
//        }

//        Toast.makeText(this, name1, Toast.LENGTH_LONG).show()
        showname!!.setText(name1)

    }
     private fun recheck(){
         Handler().postDelayed({
             init()
         },300)
     }



}