package com.example.mahasiswa_yasmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mahasiswa_yasmin.model.Mahasiswa;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_mahasiswa";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STD = "mahasiswa";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAME = "nama";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_STD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NIM + " TEXT, "
                + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STD);
        onCreate(db);
    }

    // INSERT
    public void insertMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_NAME, mahasiswa.getName());
        db.insert(TABLE_STD, null, values);
        db.close();
    }

    // GET ALL
    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STD, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                mhs.setNim(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM)));
                mhs.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                mahasiswaList.add(mhs);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswaList;
    }

    // UPDATE
    public void updateUser(int id, String nim, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_NAME, nama);
        db.update(TABLE_STD, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // DELETE
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
