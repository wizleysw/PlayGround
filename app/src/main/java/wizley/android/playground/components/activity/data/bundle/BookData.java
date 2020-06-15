package wizley.android.playground.components.activity.data.bundle;

import java.io.Serializable;

public class BookData implements Serializable {
    int id;
    String title;
    String author;
    String publisher;
    int price;

    public BookData(int id, String title, String author, String publisher, int price){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }
}

