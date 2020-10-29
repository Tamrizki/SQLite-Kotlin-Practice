package tam.pa.datamahasiswa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.datamahasiswa.R
import tam.pa.datamahasiswa.model.User

class AdapterList(ctx: Context, var list: MutableList<User>):
    RecyclerView.Adapter<AdapterList.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent, false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class viewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvAbsen: TextView = view.findViewById(R.id.tvAbsen)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvProdi: TextView = view.findViewById(R.id.tvProdi)
        fun bindItems(user: User) {
            tvAbsen.text = user.absen.toString()
            tvNama.text = user.name
            tvProdi.text = user.prody
        }
    }
}