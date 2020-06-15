package wizley.android.playground.components.activity.data.bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import wizley.android.playground.R;

public class BundleActivity extends Activity implements View.OnClickListener {
    private static String TAG = "BundleActivity";

    private TextView text_id;
    private TextView text_title;
    private TextView text_author;
    private TextView text_publisher;
    private TextView text_price;
    private Button button;

    private BookData book;
    private BookData new_book;
    private ArrayList<BookData> book_list;
    private ArrayList<BookData> new_book_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);

        button = (Button) findViewById(R.id.click);
        button.setOnClickListener(this);

        book = new BookData(1, "Little Prince", "Saint Exupery", "Wiz", 1000);

        book_list = new ArrayList<>();
        book_list.add(book);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            new_book = (BookData) bundle.getSerializable("bookInfo");
            book_list.add(new_book);

            if (new_book != null) {
                Log.e(TAG, new_book.author);
            }

            new_book_list = (ArrayList<BookData>) bundle.getSerializable("bookList");
            if (new_book_list != null) {
                Log.e(TAG, String.valueOf(new_book_list.size()));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.click){
            Intent intent = new Intent(this, BundleActivity.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("bookInfo", book);
            bundle.putSerializable("bookList", book_list);

            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}
