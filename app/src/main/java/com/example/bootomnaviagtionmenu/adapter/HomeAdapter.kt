package com.example.bootomnaviagtionmenu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bootomnaviagtionmenu.R
import com.example.bootomnaviagtionmenu.model.Home

class HomeAdapter(val list:ArrayList<Home>):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(),Filterable {


var filteredList=list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat,parent,false)
        return HomeViewHolder(view)

    }

    override fun getItemCount()=filteredList.size



    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val user = list[position]
        holder.apply {
            tvName.text = user.fullname
            tvMessage.text = user.message
            tvTime.text = user.time
            Glide.with(ivProfile).load(user.image).into(ivProfile)
            if (user.isOnline){
                ivOnline.visibility = View.VISIBLE

            }else{
                ivOnline.visibility = View.GONE
            }

        }

    }
    class HomeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ivProfile = view.findViewById<ImageView>(R.id.iv_profile)
        val ivOnline = view.findViewById<ImageView>(R.id.iv_online)
        val tvName = view.findViewById<TextView>(R.id.tv_fulname)
        val tvTime = view.findViewById<TextView>(R.id.tv_time)
        val tvMessage = view.findViewById<TextView>(R.id.tv_message)


    }

    override fun getFilter(): Filter {
        return myFilter
    }

    val myFilter = object : Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            var newList = ArrayList<Home>()
            if (p0.isNullOrEmpty()){
                newList.addAll(filteredList)

            }else{
                for (item in filteredList){
                    if (item.fullname.lowercase().contains(p0.toString().lowercase())){
                        newList.add(item)
                    }
                }
            }
            val result = FilterResults()
            result.values = newList

            return result
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            list.clear()
            list.addAll(p1?.values as ArrayList<Home>)
            notifyDataSetChanged()
        }

    }
}