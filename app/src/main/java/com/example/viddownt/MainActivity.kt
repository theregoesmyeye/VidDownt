package com.example.viddownt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.viddownt.databinding.ActivityMainBinding
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLRequest
import com.yausername.youtubedl_android.YoutubeDLResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        YoutubeDL.getInstance().init(applicationContext)

        binding.downloadButton.setOnClickListener {
            val url = binding.videoUrlInput.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.downloadProgress.visibility = View.VISIBLE

            Thread {
                try {
                    val outputDir = getExternalFilesDir(null)?.absolutePath ?: "/sdcard/Download"
                    val request = YoutubeDLRequest(url)
                    request.addOption("-o", "$outputDir/%(title)s.%(ext)s")
                    val result: YoutubeDLResponse = YoutubeDL.getInstance().execute(request)
                    runOnUiThread {
                        binding.downloadProgress.visibility = View.GONE
                        if (result.exitCode == 0) {
                            Toast.makeText(this@MainActivity, "Download Complete", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@MainActivity, "Error downloading video: ${result.err}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        binding.downloadProgress.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }.start()
        }
    }
}