package wizley.android.playground.components.provider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "myDB.db";
private const val TABLE_NAME = "user";
private const val DB_VERSION = 2;

class KTMyDBManager(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT,"+
                "name TEXT," +
                "department TEXT," +
                "age TEXT," +
                "grade INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion < newVersion){
            db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        }
    }

    fun insert(values: ContentValues?) : Long {
        return writableDatabase.insert(TABLE_NAME, null, values)
    }

    fun insertAll(values: Array<ContentValues>): Int {
        val db = writableDatabase
        db.beginTransaction()

        for(v : ContentValues in values){
            db.insert(TABLE_NAME, null, v)
        }

        db.setTransactionSuccessful()
        db.endTransaction()

        return values.size
    }

    fun query(columns: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, groupBy: String?, having: String?, orderBy: String?) : Cursor {
        return readableDatabase.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    fun update(updateRowValue: ContentValues?, whereClause: String?, whereArgs: Array<out String>?) : Int {
        return writableDatabase.update(TABLE_NAME, updateRowValue, whereClause, whereArgs)
    }

    fun delete(whereClause: String?, whereArgs: Array<out String>?) : Int {
        return writableDatabase.delete(TABLE_NAME, whereClause, whereArgs)
    }

}