package com.example.lab8_phonebook_recyclerview

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8_phonebook_recyclerview.databinding.AdapterRowBinding

class MyAdapter(private val contacts: ArrayList<Contact>, val context: Context)
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
            AlertDialog.Builder(context)
                    .setTitle("聯絡人")
                    .setMessage("確定要刪除? " + contacts[position].name)
                    .setPositiveButton("確定") {
                        dialog: DialogInterface, i: Int ->
                        //移除聯絡人
                        contacts.removeAt(position)
                        //更新列表資料
                        notifyDataSetChanged()
                    }
                    .setNeutralButton("取消") { dialog, which ->  }
                    .show()
        }

    }
    //回傳項目筆數
    override fun getItemCount(): Int {
        return contacts.size
    }
}