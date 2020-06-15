package wizley.android.playground.components.activity.data.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class BookData implements Parcelable {
    int id;
    String title;
    String author;
    String publisher;
    int price;

    protected BookData(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.author = in.readString();
        this.publisher = in.readString();
        this.price = in.readInt();
    }

    public BookData(int id, String title, String author, String publisher, int price){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(publisher);
        dest.writeInt(price);
    }

    public static final Creator<BookData> CREATOR = new Creator<BookData>() {
        @Override
        public BookData createFromParcel(Parcel in) {
            return new BookData(in);
        }

        @Override
        public BookData[] newArray(int size) {
            return new BookData[size];
        }
    };

}
