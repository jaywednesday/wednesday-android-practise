package com.wednesdaykotlinpractise.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.wednesdaykotlinpractise.models.Bank

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "FavouriteBank"
        private val TABLE_FAVOURITE_BANK = "fav_banks"
        val KEY_ID = "id"
        val KEY_BANK = "bank"
        val KEY_IFSC = "ifsc"
        val KEY_BRANCH = "branch"
        val KEY_ADDRESS = "address"
        val KEY_CONTACT = "contact"
        val KEY_CITY = "city"
        val KEY_RTGS = "rtgs"
        val KEY_DISTRICT = "district"
        val KEY_STATE = "state"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_FAV_BANK_TABLE = "CREATE TABLE $TABLE_FAVOURITE_BANK (\n" +
                "\t\"$KEY_ID\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"$KEY_BANK\"\tTEXT,\n" +
                "\t\"$KEY_IFSC\"\tTEXT,\n" +
                "\t\"$KEY_BRANCH\"\tTEXT,\n" +
                "\t\"$KEY_ADDRESS\"\tTEXT,\n" +
                "\t\"$KEY_CONTACT\"\tTEXT,\n" +
                "\t\"$KEY_CITY\"\tTEXT,\n" +
                "\t\"$KEY_RTGS\"\tNUMERIC,\n" +
                "\t\"$KEY_DISTRICT\"\tTEXT,\n" +
                "\t\"$KEY_STATE\"\tTEXT\n" +
                ");"
        db?.execSQL(CREATE_FAV_BANK_TABLE);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun doesBankExistIfsc(ifsc: String) : Boolean {
        val db = this.readableDatabase
        val selectionArgs = arrayOf(ifsc)
        val c = db.rawQuery("SELECT ${KEY_ID} FROM ${TABLE_FAVOURITE_BANK} where ${KEY_IFSC} = ? ", selectionArgs)
        return c.count > 0
    }
    fun getAllBanks() : ArrayList<Bank> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM ${TABLE_FAVOURITE_BANK}", null)
        val banks = ArrayList<Bank>()
        if (c.moveToFirst()) {
            do {
                val bank = Bank(
                    c.getInt(c.getColumnIndex(KEY_ID)),
                    c.getString(c.getColumnIndex(KEY_BANK)),
                    c.getString(c.getColumnIndex(KEY_IFSC)),
                    c.getString(c.getColumnIndex(KEY_BRANCH)),
                    c.getString(c.getColumnIndex(KEY_ADDRESS)),
                    c.getString(c.getColumnIndex(KEY_CONTACT)),
                    c.getString(c.getColumnIndex(KEY_CITY)),
                    c.getInt(c.getColumnIndex(KEY_RTGS)) == 0,
                    c.getString(c.getColumnIndex(KEY_DISTRICT)),
                    c.getString(c.getColumnIndex(KEY_STATE))
                )
                banks.add(bank)
            } while (c.moveToNext())
        }
        return banks
    }
    fun createBank(cv: ContentValues) : Long {
        val db = this.writableDatabase
        val success = db.insert(TABLE_FAVOURITE_BANK, null, cv)
        db.close()
        return success
    }
    fun deleteBank(id: Int) : Int {
        val db = this.writableDatabase
        val whereArgs = arrayOf(id.toString())
        val success = db.delete(TABLE_FAVOURITE_BANK, "${KEY_ID} = ?", whereArgs)
        db.close()
        return success
    }

}