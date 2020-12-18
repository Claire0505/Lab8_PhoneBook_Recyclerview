package com.example.lab8_phonebook_recyclerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8_phonebook_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    private val contacts = ArrayList<Contact>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //判斷Bundle是否不為空，「?.」須先確認不是null才可以使用， 「?:」替代if()else運算式的方法
        val bundle = data?.extras?: return
        //驗證發出對象與回傳狀態
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            //新增聯絡人
            contacts.add(Contact(bundle.getString("name", ""),
                                bundle.getString("phone", "")))
            //更新列表資料
            myAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        //創建LinearLayoutManager物件
        val layoutManager = LinearLayoutManager(this)
        //設定垂直顯示
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        myAdapter = MyAdapter(contacts, this)
        //連結adapter
        binding.recyclerView.adapter = myAdapter

        binding.btnAddContact.setOnClickListener {
            //透過startActivityForResult發出Intent，並紀錄請求對象
            startActivityForResult(Intent(this, MainActivity2::class.java),1)
        }
    }
}