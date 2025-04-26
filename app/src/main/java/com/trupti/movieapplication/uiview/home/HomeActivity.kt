package com.trupti.movieapplication.uiview.home

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.trupti.movieapplication.R

class HomeActivity : AppCompatActivity() {



    private lateinit var toggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toggleButton = findViewById(R.id.toggleButton)



        supportFragmentManager.commit {
            replace(R.id.fragment_container, MovieFragment())
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            supportFragmentManager.commit {
                replace(
                    R.id.fragment_container,
                    if (isChecked) TvShowFragment() else MovieFragment()
                )
            }
        }
    }
}

