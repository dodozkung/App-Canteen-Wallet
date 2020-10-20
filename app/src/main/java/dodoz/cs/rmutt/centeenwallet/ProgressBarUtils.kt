package dodoz.cs.rmutt.centeenwallet

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler

class ProgressBarUtils (context: Context){

    private var progressDialog: Dialog = Dialog(context)

    fun showLoadingDialog() {
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
    }

    fun showLoading() {
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.loading_content_layout)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        val handler = Handler()
        handler.postDelayed({
            if (progressDialog.isShowing){
                progressDialog.dismiss()
            }
        },30000)
    }

    fun hideLoadingDialog() {
        progressDialog.dismiss()
    }

}