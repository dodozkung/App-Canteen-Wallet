package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnconotp!!.setOnClickListener {
            val intent = Intent(this, VerifyActivity::class.java)

            startActivity(intent)
        }
        }

    }
