package dodoz.cs.rmutt.canteenwallet.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dodoz.cs.rmutt.canteenwallet.R
import dodoz.cs.rmutt.canteenwallet.model.userRecy
import kotlinx.android.synthetic.main.list_report.view.*


class ReportAdapter(private val users: List<userRecy>) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    // Save the expanded row position
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_report, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val user = users[position]

        val a1 = user.Date!!.split(" ").toTypedArray()

        val a2 = a1[0].split("-").toTypedArray()

        val a3 = a2[2] + "/" + a2[1] + "/" + a2[0] + " " + a1[1]

        holder.Date.text = a3
//        holder.Date.text = user.Date


        if(user.Typetransfer == "transfer"){
            holder.Typetransfer.text = "โอน"

        }else if (user.Typetransfer == "pay"){
            holder.Typetransfer.text = "จ่าย"
        }else if (user.Typetransfer == "deposit"){
            holder.Typetransfer.text = "เติมเงิน"
        }else if (user.Typetransfer == "withdraw"){
            holder.Typetransfer.text = "ถอนเงิน"
        }
        holder.Amount.text = user.Amount.toString()

    }



    override fun getItemCount() = users.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Date: TextView = itemView.getdate
        val Typetransfer: TextView = itemView.gettype
        val Amount: TextView = itemView.getcount1

//        fun bind(item: user) {
//
//            itemView.apply {
//                if(item?.transfer.equals("pay")){
//                    report1.setBackgroundResource(R.color.yellow)
//                    getcount1.text = item?.amount
//                    yord.text = "ยอดจ่าย"
//
//                }else if(item?.transfer.equals("transfer")){
//                    report1.setBackgroundResource(R.color.Rmutt3)
//                    getcount1.text = item?.amount
//                    yord.text = "ยอดรับ"
//                }
//
//            }

//            itemView.Check_report.setOnClickListener {
//                var intent = Intent(context, TransferActivity::class.java)
//                context.startActivity(intent)
//                (context as Activity).finish()

//            }
//
//        }
    }

}
