package wizley.android.playground.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import wizley.android.playground.R;

public class CameraActivity extends AppCompatActivity {
    private static final String TAG = "CameraActivity";

    private ImageView imageView;

    private static final int REQUEST_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);

        imageView = findViewById(R.id.imageView);

        if(checkCamera(getApplicationContext())) {
            takePhoto();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void takePhoto(){
        Log.e(TAG, "takePhoto");
        ActivityResultLauncher<Integer> launcher = registerForActivityResult(new CameraContract(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap img) {
                if(img == null) return;
                imageView.setImageBitmap(img);
                Log.e(TAG, "Bitmap OK");
            }
        });
        launcher.launch(REQUEST_IMAGE_CAPTURE);
    }

    static class CameraContract extends ActivityResultContract<Integer, Bitmap>{

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Integer input) {
            Log.e(TAG, "createIntent");
            return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        }

        @Override
        public Bitmap parseResult(int resultCode, @Nullable Intent intent) {
            Log.e(TAG, "parseResult");
            if(resultCode == RESULT_OK){
                Log.e(TAG, "OK");

                if(intent != null) {
                    Bundle extras = intent.getExtras();
                    assert extras != null;
                    return (Bitmap) extras.get("data");
                }
            }
            return null;
        }
    }

    String currentPhotoPath;
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();

        Log.e(TAG, currentPhotoPath);
        return image;
    }

    private boolean checkCamera(Context context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
