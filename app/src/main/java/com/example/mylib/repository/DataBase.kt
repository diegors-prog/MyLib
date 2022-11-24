package com.example.mylib.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mylib.constants.DataBaseConstants

class DataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "mylibrarydb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + DataBaseConstants.BOOK.TABLE_NAME + " (" +
                DataBaseConstants.BOOK.COLUMNS.ID + " integer primary key autoincrement, " +
                DataBaseConstants.BOOK.COLUMNS.TITLE + " text, " +
                DataBaseConstants.BOOK.COLUMNS.DESCRIPTION + " text, " +
                DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES + " integer, " +
                DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME + " text, " +
                DataBaseConstants.BOOK.COLUMNS.STATUS + " integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}