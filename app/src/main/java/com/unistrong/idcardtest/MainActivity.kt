package com.unistrong.idcardtest

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        findViewById<Button>(R.id.button).setOnClickListener {
            openNFCActivity()
        }
    }

    private fun openNFCActivity() {
        startActivityForResult(
            Intent.createChooser(
                Intent(Intent.ACTION_VIEW).setData(Uri.parse("idcard://dev.unistrong.com")),
                "No Activity found to handle Intent"
            ),
            REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            data?.let {

                var name = it.getStringExtra("name")
                var sex = it.getStringExtra("sex")
                var idCode = it.getStringExtra("idCode")
                var address = it.getStringExtra("address")
                var birthday = it.getStringExtra("birthday")
                var photo = it.getParcelableExtra<Bitmap>("photo")
                var department = it.getStringExtra("department")
                var date = it.getStringExtra("date")
                var category = it.getStringExtra("category")
                var hometown = if (category.equals(
                        "GAT",
                        ignoreCase = true
                    )
                ) it.getStringExtra("passnumber") else it.getStringExtra("nation")

                Log.d(
                    TAG,
                    "$photo,$name,$sex,$birthday,$idCode,$address,$department,$date,$category,$hometown"
                )
                findViewById<ImageView>(R.id.imageView).setImageBitmap(photo)
                findViewById<TextView>(R.id.textView).text =
                    "皓\n$sex\n$birthday\n$00000000000\n$address\n$department\n$date\n$category\n$hometown"
            }

        }
    }
}