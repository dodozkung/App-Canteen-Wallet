package dodoz.cs.rmutt.centeenwallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dodoz.cs.rmutt.centeenwallet.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transfermain!!.setOnClickListener {
            val intent = Intent(this,TransferActivity::class.java)
            startActivity(intent)
        }



    }
}