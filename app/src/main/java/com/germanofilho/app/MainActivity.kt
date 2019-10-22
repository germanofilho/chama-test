package com.germanofilho.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.germanofilho.app.core.view.BaseActivity
import com.germanofilho.chamaapp.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
