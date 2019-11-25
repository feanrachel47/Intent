package com.example.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener{
            sendMessage()
        }

        buttonCallMe.setOnClickListener{
            dialPhone()
        }
    }

    private fun dialPhone() {
        val intent = Intent(Intent.ACTION_VIEW)
        val phone : String="tel: 01127210802"  //uri = tel for phone, https for website

        //check package manager for app to handle the intent
        intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager)!==null){
            startActivity(intent)

        }
    }


    private fun sendMessage() {
        //explicit intent
        val intent = Intent(this, SecondActivity::class.java)
        val message = editTextMsg.text.toString()
        val luckyNum = editTextNum.text.toString().toInt()

        intent.putExtra(KEY, message)
        intent.putExtra(NUM,luckyNum)


        //startActivity(intent) //An intent without return value
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE){

            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(REPLY).toString()
                textViewReply.text = String.format("%s %s", getString(R.string.reply),reply)
            }
        }
    }


    companion object{
        const val KEY = "com.example.intent.KEY" //package name is unique so can set as constant
        const val NUM = "com.example.intent.NUM"
        const val REPLY = "com.example.intent.REPLY"
        const val REQUEST_CODE = 1
    }


}
