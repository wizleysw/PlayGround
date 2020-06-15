package wizley.android.playground.components.activity.data.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import wizley.android.playground.R;

public class ParcelableActivity extends Activity implements View.OnClickListener {
    private static String TAG = "ParcelableActivity";

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

        SetBook();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Log.e(TAG, "parcelable result");
            new_book = bundle.getParcelable("bookInfo");
            book_list.add(new_book);

            text_id = (TextView) findViewById(R.id.id);
            text_title = (TextView) findViewById(R.id.title);
            text_author = (TextView) findViewById(R.id.author);
            text_publisher = (TextView) findViewById(R.id.publisher);
            text_price = (TextView) findViewById(R.id.price);

            text_id.setText(String.valueOf(new_book.id));
            text_title.setText(new_book.title);
            text_author.setText(new_book.author);
            text_publisher.setText(new_book.publisher);
            text_price.setText(String.valueOf(new_book.price));
        }

        Intent intent = getIntent();
        new_book_list = intent.getParcelableArrayListExtra("bookList");
        if(new_book_list != null) {
            Log.e(TAG, String.valueOf(new_book_list.size()));
        }
    }

    private void SetBook(){
        book = new BookData(1, "Little Prince", "Saint Exupery", "Wiz", 1000);

        book_list = new ArrayList<>();
        book_list.add(book);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.click) {
            Intent intent = new Intent(this, ParcelableActivity.class);
            if(book_list.size() > 1){
                intent.putParcelableArrayListExtra("bookList", book_list);
            }
            intent.putExtra("bookInfo", book);
            startActivity(intent);
        }
    }
}
