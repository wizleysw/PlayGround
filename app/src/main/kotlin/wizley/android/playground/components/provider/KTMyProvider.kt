package wizley.android.playground.components.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class KTMyProvider : ContentProvider() {
    private lateinit var dbManager : KTMyDBManager

    override fun onCreate(): Boolean {
        dbManager = KTMyDBManager(context)
        return true;
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val row = dbManager.insert(values)
        return null
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return dbManager.query(projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return dbManager.update(values, selection, selectionArgs)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return dbManager.delete(selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        return null
    }

}