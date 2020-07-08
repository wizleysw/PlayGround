package wizley.android.playground.components.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// reference from :  https://choidev-1.tistory.com/56
public class MyDBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDB.db";
    private static final String TABLE_NAME = "user";
    private static final int DB_VERSION = 2;

    private Context context = null;
    private static MyDBManager dbManager = null;

    public static MyDBManager getInstance(Context context){
        if(dbManager == null){
            dbManager = new MyDBManager(context, DB_NAME, null, DB_VERSION);
        }
        return dbManager;
    }

    private MyDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT,"+
                "name TEXT," +
                "department TEXT," +
                "age TEXT," +
                "grade INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
    }

    public long insert(ContentValues values){
        return getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public int insertAll(ContentValues[] values){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        for(ContentValues v : values){
            db.insert(TABLE_NAME, null, v);
        }

        db.setTransactionSuccessful();;
        db.endTransaction();

        return values.length;
    }

    public Cursor query(String [] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues updateRowValue,String whereClause, String[] whereArgs){
        return getWritableDatabase().update(TABLE_NAME, updateRowValue, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(TABLE_NAME, whereClause, whereArgs);
    }
}
