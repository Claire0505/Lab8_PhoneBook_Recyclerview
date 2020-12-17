package com.example.lab8_phonebook_recyclerview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab8_phonebook_recyclerview.databinding.ActivityMain2Binding
import java.time.Duration

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            //判斷是否輸入姓名和電話
            when {
                binding.edName.length() < 1 -> {
                    showToast(this, "請輸入姓名")
                }
                binding.edPhone.length() < 1 -> {
                    showToast(this, "請輸入電話")
                }
                else -> {
                    //將姓名和電話放入Bundle
                    val bundle = Bundle()
                    bundle.putString("name", binding.edName.text.toString())
                    bundle.putString("phone", binding.edPhone.text.toString())
                    //透過 seResult 將資料回傳
                    setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
                    //結束Main2
                    finish()
                }
            }

        }

    }

     @SuppressLint("showToast")
     private fun showToast(context: Context, message : String ){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
/**
 *  原來的寫法，上面是kotlin建議的新寫法
 //判斷是否輸入姓名和電話
if (binding.edName.length() < 1){
ShowToast(this, "請輸入姓名")
}else if (binding.edPhone.length() < 1){
ShowToast(this, "請輸入電話")
} else{
//將姓名和電話放入Bundle
val bundle = Bundle()
bundle.putString("name", binding.edName.text.toString())
bundle.putString("phone", binding.edPhone.text.toString())
//透過 seResult 將資料回傳
setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
//結束Main2
finish()
}
 *
 * */