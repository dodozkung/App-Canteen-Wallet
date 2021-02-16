package dodoz.cs.rmutt.canteenwallet.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.Transfer.TransferActivity
import dodoz.cs.rmutt.canteenwallet.model.user
import kotlinx.android.synthetic.main.list_report.view.*


class ReportAdapter(private val orderList: ArrayList<user>, private var context: Context) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    // Save the expanded row position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_report, parent, false)

        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderList?.get(position))


    }

    override fun getItemCount(): Int {
        return orderList?.size!!
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: user) {

            itemView.apply {
                if(item?.transfer.equals("pay")){
                    report1.setBackgroundResource(R.color.yellow)
                    getcount1.text = item?.amount
                    yord.text = "ยอดจ่าย"

                }else if(item?.transfer.equals("transfer")){
                    report1.setBackgroundResource(R.color.Rmutt3)
                    getcount1.text = item?.amount
                    yord.text = "ยอดรับ"
                }

            }

            itemView.Check_report.setOnClickListener {
                var intent = Intent(context, TransferActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()

            }

        }
    }

}
