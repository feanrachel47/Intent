package com.example.intent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.KEY)
        val luckyNum = intent.getIntExtra(MainActivity.NUM, 0)
        textViewMsg.text = String.format("%s %s", getString(R.string.message),message)
        textViewLuckyNum.text = String.format("%s %d", getString(R.string.lucky_number),luckyNum)



        buttonDone.setOnClickListener{

            if(editTextReply.text.isNotEmpty()) {
                val reply = editTextReply.text.toString()
                val intent = getIntent() //return MainActivity intent
                intent.putExtra(MainActivity.REPLY, reply)

                //inform MainActivity everything is ok
                setResult(Activity.RESULT_OK, intent)
            }else
                setResult(Activity.RESULT_CANCELED)

            finish()
        }
    }
}
