package wizley.android.playground.components.activity.implicit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import wizley.android.playground.R;

public class GetContentActivity extends AppCompatActivity {
    private static final String TAG = "GetContentActivity";
    private static final int REQUEST_IMAGE_GET = 1;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getcontent);

        imageView = (ImageView) findViewById(R.id.imageView);

        selectImage();

    }

    public void selectImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if(intent.resolveActivity(getPackageManager()) !=null ){
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Uri img = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), img);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert img != null;
                Log.e(TAG, img.getPath());
            }
        }
    }
}
