package com.desafiolatam.surveydonkey.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.desafiolatam.surveydonkey.databinding.ActivityMainBinding
import com.desafiolatam.surveydonkey.ui.adapter.SurveyPagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewPager: SurveyPagerAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                permissionGranted()
            } else {
                permissionDenied()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermission()

        viewPager = SurveyPagerAdapter(this)
        binding.vpMain.adapter = viewPager

        binding.fab.setOnClickListener {
            when (binding.vpMain.currentItem) {
                0 -> binding.vpMain.setCurrentItem(1, true)
                1 -> binding.vpMain.setCurrentItem(2, true)
                2 -> binding.vpMain.setCurrentItem(3, true)
                3 -> binding.vpMain.setCurrentItem(4, true)
                4 -> binding.vpMain.setCurrentItem(5, true)
            }
        }
    }

    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                permissionGranted()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) -> {
                Toast.makeText(this, "Solicitar nuevamente", Toast.LENGTH_SHORT).show()
            }else ->{
                requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }

    }

    private fun permissionGranted() {
        Toast.makeText(this, "permiso concedido", Toast.LENGTH_SHORT).show()
    }

    private fun permissionDenied() {
        Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
    }
}
