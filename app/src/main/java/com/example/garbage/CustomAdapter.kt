package com.example.garbage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.garbage.databinding.ItemViewBinding

class CustomAdapter(val dataList: MutableList<DataList>) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemViewBinding: ItemViewBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val itemViewBinding = holder.itemViewBinding
        itemViewBinding.tvName.text = dataList.get(position).tvName
        itemViewBinding.tvAge.text = dataList.get(position).tvAge
        itemViewBinding.tvEmail.text = dataList.get(position).tvEmail
        itemViewBinding.ivPicture.setImageResource(dataList.get(position).ivpicture)
        itemViewBinding.root.setOnClickListener {
            Toast.makeText(
                itemViewBinding.root.context,
                "${itemViewBinding.tvName.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
        itemViewBinding.root.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                val position = holder.adapterPosition
                val dataList = dataList.removeAt(position)

                Toast.makeText(itemViewBinding.root.context, " ${dataList.tvName} 삭제 완료", Toast.LENGTH_SHORT)
                    .show()
                notifyDataSetChanged()
                return true
            }
        })
    }

    class CustomViewHolder(val itemViewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)
}