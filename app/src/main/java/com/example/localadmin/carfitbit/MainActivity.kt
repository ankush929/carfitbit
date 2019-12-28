package com.example.localadmin.carfitbit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

        if (v?.id==R.id.siren)
        {
            Toast.makeText(this, "Is clicked", Toast.LENGTH_SHORT )
        }

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var button: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.siren) as ImageButton
        button.setOnClickListener(this)

    }


}