package com.example.mylib.repository

import android.content.ContentValues
import android.content.Context
import com.example.mylib.constants.DataBaseConstants
import com.example.mylib.model.BookModel

class BookRepository private constructor(context: Context) {
    private val dataBase = DataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: BookRepository

        fun getInstance(context: Context): BookRepository {
            if (!::repository.isInitialized) {
                repository = BookRepository(context)
            }
            return repository
        }
    }

    fun insert(book: BookModel): Boolean {
        return try {
            val db = dataBase.writableDatabase

            val status = if (book.status) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.BOOK.COLUMNS.TITLE, book.title)
            values.put(DataBaseConstants.BOOK.COLUMNS.DESCRIPTION, book.description)
            values.put(DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES, book.numberOfPages)
            values.put(DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME, book.authorName)
            values.put(DataBaseConstants.BOOK.COLUMNS.STATUS, status)

            db.insert(DataBaseConstants.BOOK.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(book: BookModel): Boolean {
        return try {
            val db = dataBase.writableDatabase

            val status = if (book.status) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.BOOK.COLUMNS.TITLE, book.title)
            values.put(DataBaseConstants.BOOK.COLUMNS.DESCRIPTION, book.description)
            values.put(DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES, book.numberOfPages)
            values.put(DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME, book.authorName)
            values.put(DataBaseConstants.BOOK.COLUMNS.STATUS, status)

            val selection = DataBaseConstants.BOOK.COLUMNS.ID + " = ?"
            val args = arrayOf(book.id.toString())

            db.update(DataBaseConstants.BOOK.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = dataBase.writableDatabase

            val selection = DataBaseConstants.BOOK.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.BOOK.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<BookModel> {
        val bookList = mutableListOf<BookModel>()
        try {
            val db = dataBase.readableDatabase

            val selection = arrayOf(
                DataBaseConstants.BOOK.COLUMNS.ID,
                DataBaseConstants.BOOK.COLUMNS.TITLE,
                DataBaseConstants.BOOK.COLUMNS.DESCRIPTION,
                DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES,
                DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME,
                DataBaseConstants.BOOK.COLUMNS.STATUS
            )

            val cursor = db.query(
                DataBaseConstants.BOOK.TABLE_NAME, selection, null, null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.ID))
                    val title = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.TITLE))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.DESCRIPTION))
                    val numberOfPages = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES))
                    val authorName = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME))
                    val status = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.STATUS))

                    val book = BookModel(id, title, description, numberOfPages, authorName, status == 1)
                    bookList.add(book)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return bookList
        }
        return bookList
    }

    fun getAllReadBook(): List<BookModel> {
        val bookList = mutableListOf<BookModel>()
        try {
            val db = dataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, title, description, number_of_pages, author_name, status FROM Book WHERE status = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.ID))
                    val title = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.TITLE))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.DESCRIPTION))
                    val numberOfPages = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES))
                    val authorName = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME))
                    val status = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.STATUS))

                    val book = BookModel(id, title, description, numberOfPages, authorName, status == 1)
                    bookList.add(book)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return bookList
        }
        return bookList
    }

    fun getAllUnreadBook(): List<BookModel> {
        val bookList = mutableListOf<BookModel>()
        try {
            val db = dataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, title, description, number_of_pages, author_name, status FROM Book WHERE status = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.ID))
                    val title = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.TITLE))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.DESCRIPTION))
                    val numberOfPages = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.NUMBER_OF_PAGES))
                    val authorName = cursor.getString(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.AUTHOR_NAME))
                    val status = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.BOOK.COLUMNS.STATUS))

                    val book = BookModel(id, title, description, numberOfPages, authorName, status == 1)
                    bookList.add(book)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return bookList
        }
        return bookList
    }
}