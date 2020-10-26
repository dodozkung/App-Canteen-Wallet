package dodoz.cs.rmutt.canteenwallet.Login

import android.content.Intent
import android.os.Bundle
import dodoz.cs.rmutt.canteenwallet.BaseActivity
import dodoz.cs.rmutt.canteenwallet.MainActivity
import dodoz.cs.rmutt.canteenwallet.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnconprofile!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}