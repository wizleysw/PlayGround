package wizley.android.playground.storage

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class KTFileAccessActivity : AppCompatActivity(){
    private val TAG = "KTFileAccessActivity"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        createDir("NewDir")
        createFile("abc")
        readFile("abc")
        getFileList()

        getAppSpecificAlbumStorageDir(applicationContext, "playGround")
        checkStorageAvailability()
    }

    private fun getFileList(){
        Log.e(TAG, "getFileList")
        val files = applicationContext.fileList()
        Log.e(TAG, files.size.toString())
        for(file in files){
            Log.e(TAG, file)
        }
    }

    private fun createDir(filename : String){
        Log.e(TAG, "createDir")
        val directory = applicationContext.filesDir
        Log.e(TAG, directory.absolutePath)
        val file = File(directory, filename)
        if(!file.mkdirs()){
            Log.e(TAG, "failed")
        } else {
            Log.e(TAG, "success")
        }
    }

    private fun createFile(filename : String){
        Log.e(TAG, "createFile")
        val directory = applicationContext.filesDir

        val file = File(directory.path + "/NewDir", filename)
        file.createNewFile()

        val contents = "this is abc file"
        val fw = FileWriter(file)
        fw.write(contents)
        fw.close()
    }

    private fun readFile(filename: String){
        Log.e(TAG, "readFile")
        val directory = applicationContext.filesDir

        val file = File(directory.path + "/NewDir", filename)

        if(!file.exists()){
            Log.e(TAG, "file does not exists")
            return
        }

        val fr = FileReader(file)
        val bufferedReader = BufferedReader(fr)

        bufferedReader.forEachLine { it ->
            Log.e(TAG, it)
        }
        bufferedReader.close()
        fr.close()
    }

    private fun isExternalStorageWritable() : Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadable() : Boolean {
        return isExternalStorageWritable() || Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun getAppSpecificAlbumStorageDir(context : Context, albumName : String) : File {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName)
        Log.e(TAG, file.absolutePath)

        if(!file.mkdirs()){
            Log.e(TAG, "failed")
        } else {
            Log.e(TAG, "success")
        }
        return file
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkStorageAvailability(){
        val NUM_BYTES_NEED_FOR_APP = 1024 * 1024 * 10L

        val storageManager = applicationContext.getSystemService(StorageManager::class.java)
        val appSpecificInternalDirUuid = storageManager.getUuidForPath(filesDir)

        val availableBytes = storageManager.getAllocatableBytes(appSpecificInternalDirUuid)

        if(availableBytes >= NUM_BYTES_NEED_FOR_APP){
            storageManager.allocateBytes(appSpecificInternalDirUuid, NUM_BYTES_NEED_FOR_APP)
        } else {
            val storageIntent = Intent()
            storageIntent.setAction(Intent.ACTION_MANAGE_PACKAGE_STORAGE)
            startActivity(storageIntent)
        }
    }
}