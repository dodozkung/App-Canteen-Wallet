package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity
import dodoz.cs.rmutt.canteenwallet.PayQrcode.PayQrcodeActivity
import dodoz.cs.rmutt.canteenwallet.Transfer.TransferActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    SesionManager sessionManager;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SessionManager = SessionManager(this,)

        transfermain!!.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        payqrcodemain!!.setOnClickListener {
            val intent = Intent(this, PayQrcodeActivity::class.java)
            startActivity(intent)
        }



    }
}

