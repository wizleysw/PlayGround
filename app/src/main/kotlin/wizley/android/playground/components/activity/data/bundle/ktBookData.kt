package wizley.android.playground.components.activity.data.bundle

import java.io.Serializable

class ktBookData() : Serializable {
    var id: Int? = -1
    var title: String? = null
    var author: String? = null
    var publisher: String? = null
    var price: Int? = -1

    constructor(id: Int, title: String, author: String, publisher: String, price: Int) : this() {
        this.id = id
        this.title = title
        this.author = author
        this.publisher = publisher
        this.price = price
    }
}