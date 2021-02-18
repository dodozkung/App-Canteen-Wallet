package dodoz.cs.rmutt.canteenwallet

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dodoz.cs.rmutt.canteenwallet.Login.LoginActivity
import dodoz.cs.rmutt.canteenwallet.Retrofit.SharedPrefManager
import dodoz.cs.rmutt.canteenwallet.Transfer.TransferConfirmActivity
import kotlin.system.exitProcess

class SplashActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed({
//            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            finish()
            checkconntype()
        },3000)



    }

    private fun checkconntype(){
        val connManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wificonn =  connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wificonn.isConnectedOrConnecting){
                isonline()
//                Toast.makeText(applicationContext, "me1", Toast.LENGTH_LONG).show()
            }else{
                if (mobile.isConnectedOrConnecting){

                    isonline()

                }else{

                    internetisnotconnectedDialog()

                }
//                    Toast.makeText(applicationContext, "maime0", Toast.LENGTH_LONG).show()
            }
    }

    private fun isonline(){
        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }else {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun internetisnotconnectedDialog() {
        val inflater = LayoutInflater.from(this)
        val subView = inflater.inflate(R.layout.check_internet, null)
        val ndely = subView.findViewById<Button>(R.id.btnok)

        val builder = AlertDialog.Builder(this)
        builder.setView(subView)
        val dialog = builder.create()
        dialog.setCancelable(false)
        ndely.setOnClickListener {
            dialog.dismiss()
            exitProcess(1)
        }
        if (!this.isFinishing) {
            dialog.show()

        }
    }


    }
