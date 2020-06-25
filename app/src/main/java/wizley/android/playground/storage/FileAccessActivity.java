package wizley.android.playground.storage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FileAccessActivity extends AppCompatActivity {
    private static final String TAG = "FileAccessActivity";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

        createDir("NewDir");
        try {
            createFile("abc");
            readFile("abc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        getFileList();

        getAppSpecificAlbumStorageDir(getApplicationContext(), "playGround");

        try {
            checkStorageAvailability();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getFileList(){
        Log.e(TAG, "getFileList");
        String[] files = getApplicationContext().fileList();
        Log.e(TAG, String.valueOf(files.length));
        for(String file : files){
            Log.e(TAG, file);
        }
    }

    private void createDir(String filename){
        Log.e(TAG, "createDir");
        File directory = getApplicationContext().getFilesDir();
        Log.e(TAG, directory.getAbsolutePath());
        File file = new File(directory, filename);
        if(!file.mkdirs()){
            Log.e(TAG, "failed");
        } else {
            Log.e(TAG, "success");
        }
    }

    private void createFile(String filename) throws IOException {
        Log.e(TAG, "createFile");
        File directory = getApplicationContext().getFilesDir();

        File file = new File(directory + "/NewDir", filename);
        file.createNewFile();

        String contents = "this is abc file";

        FileWriter fw = new FileWriter(file);
        fw.write(contents);
        fw.close();
    }

    private void readFile(String filename) throws IOException {
        Log.e(TAG, "readFile");
        File directory = getApplicationContext().getFilesDir();

        File file = new File(directory + "/NewDir", filename);

        if(!file.exists()){
            Log.e(TAG, "file does not exists");
            return;
        }

        FileReader fr = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fr);
        String line;
        while((line = bufferedReader.readLine()) != null){
            Log.e(TAG, line);
        }
        bufferedReader.close();
        fr.close();
    }

    private boolean isExernalStorageWritable(){
       return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private boolean isExternalStorageReadable(){
        return isExernalStorageWritable() || Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
    }

    private File getAppSpecificAlbumStorageDir(Context context, String albumName){
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName);
        Log.e(TAG, file.getAbsolutePath());

        if(!file.mkdirs()){
           Log.e(TAG, "failed");
        } else {
            Log.e(TAG, "success");
        }
        return file;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkStorageAvailability() throws IOException {
        Log.e(TAG, "checkStorage");
        long NUM_BYTES_NEED_FOR_APP = 1024 * 1024 * 10L;

        StorageManager storageManager = getApplicationContext().getSystemService(StorageManager.class);

        UUID appSpecificInternalDirUuid = storageManager.getUuidForPath(getFilesDir());

        long availableBytes = storageManager.getAllocatableBytes(appSpecificInternalDirUuid);
        Log.e(TAG, String.valueOf(availableBytes));

        if(availableBytes >= NUM_BYTES_NEED_FOR_APP){
            storageManager.allocateBytes(appSpecificInternalDirUuid, NUM_BYTES_NEED_FOR_APP);
        } else {
            Intent storageIntent = new Intent();
            storageIntent.setAction(Intent.ACTION_MANAGE_PACKAGE_STORAGE);
            startActivity(storageIntent);
        }
    }
}
