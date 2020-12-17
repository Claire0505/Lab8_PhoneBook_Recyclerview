package com.example.lab8_phonebook_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8_phonebook_recyclerview.databinding.AdapterRowBinding

class MyAdapter(private val contacts: ArrayList<Contact>)
    : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    //ViewHolder類別，用來緩存畫面中的元件
    class ViewHolder(var binding: AdapterRowBinding): RecyclerView.ViewHolder(binding.root)

    //建立ViewHolder並連結畫面
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val binding = AdapterRowBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return ViewHolder(binding)
    }
    //連結項目資料與元件
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = contacts[position].name
        holder.binding.tvPhone.text = contacts[position].phone

        //設定按鈕監聽事件，使用removeAt()刪除指定位置的資料
        holder.binding.imgDelete.setOnClickListener {
            //移除聯絡人
            contacts.removeAt(position)
            //更新列表資料
            notifyDataSetChanged()
        }

    }
    //回傳項目筆數
    override fun getItemCount(): Int {
        return contacts.size
    }
}