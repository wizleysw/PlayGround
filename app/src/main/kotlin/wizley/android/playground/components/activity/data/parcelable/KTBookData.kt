package wizley.android.playground.components.activity.data.parcelable

import android.os.Parcel
import android.os.Parcelable

class KTBookData() : Parcelable {
    var id : Int? = -1
    var title : String? = null
    var author : String? = null
    var publisher : String? = null
    var price : Int? = -1

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        author = parcel.readString()
        publisher = parcel.readString()
        price = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    constructor(id: Int, title: String, author: String, publisher: String, price: Int) : this() {
        this.id = id
        this.title = title
        this.author = author
        this.publisher = publisher
        this.price = price
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(publisher)
        parcel.writeValue(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KTBookData> {
        override fun createFromParcel(parcel: Parcel): KTBookData {
            return KTBookData(parcel)
        }

        override fun newArray(size: Int): Array<KTBookData?> {
            return arrayOfNulls(size)
        }
    }

}