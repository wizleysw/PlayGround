package wizley.android.playground.components.activity.implicit

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTGetContentActivity : AppCompatActivity(){
    private val TAG = "KTGetContentActivity"
    private val REQUEST_IMAGE_GET = 1

    var imageView : ImageView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getcontent)

        imageView = findViewById(R.id.imageView)

        selectImage()

    }

    @SuppressLint("WrongConstant")
    fun selectImage(){
        intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if(intent.resolveActivity(packageManager) != null){
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_GET){
            if(resultCode == RESULT_OK){
                val img : Uri? = data!!.data
                val bitmap : Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, img)
                imageView!!.setImageBitmap(bitmap)
                Log.e(TAG, img!!.path)
            }
        }
    }

}