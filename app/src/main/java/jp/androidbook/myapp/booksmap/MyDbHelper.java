package jp.androidbook.myapp.booksmap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDbHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "myDatabase.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "myData";
    static final String ID = "_id";
    static final String NAME = "name";                  //名前
    static final String ADDRESS = "address";           //住所
    static final String TELL = "tell";                  //電話番号
    static final String EVALUATION = "evaluation";    //評価(5段階評価 5がMax)
    static final String COMENT = "coment";             //一言コメント(雰囲気、広さなどなど)

    public MyDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(" +ID + " INTEGER PRIMARY KEY autoincrement," + NAME + " TEXT," + ADDRESS + " TEXT," + TELL + " TEXT," + EVALUATION + " TEXT," + COMENT + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
