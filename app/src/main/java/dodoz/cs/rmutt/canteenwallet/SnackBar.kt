package dodoz.cs.rmutt.canteenwallet

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackBar {
    fun snackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }
}