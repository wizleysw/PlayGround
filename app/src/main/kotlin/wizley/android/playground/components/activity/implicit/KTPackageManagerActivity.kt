package wizley.android.playground.components.activity.implicit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import wizley.android.playground.R
import android.widget.Toast.makeText as makeText1

class KTPackageManagerActivity : Activity(), View.OnClickListener {
    private val TAG = "ktPackageManagerActivity"
    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "PhoneCall with PackageManager"
        button!!.setOnClickListener(this)
    }

    @SuppressLint("LongLogTag")
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                val callIntent: Intent = Uri.parse("tel:5551234").let {
                    number -> Intent(Intent.ACTION_DIAL, number)
                }

                val activities : List<ResolveInfo> = packageManager.queryIntentActivities(callIntent, 0)
                val isIntentSafe : Boolean = activities.isNotEmpty()

                Log.e(TAG, activities.size.toString())

                if(isIntentSafe) {
                    startActivity(callIntent)
                } else {
                    Toast.makeText(this, "Calling this Intent is not safe", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}