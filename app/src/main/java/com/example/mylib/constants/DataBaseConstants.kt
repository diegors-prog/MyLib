package com.example.mylib.constants

class DataBaseConstants private constructor() {

    object BOOK {
        const val ID = "bookId"
        const val TABLE_NAME = "Book"

        object COLUMNS {
            const val ID = "id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val NUMBER_OF_PAGES = "number_of_pages"
            const val AUTHOR_NAME = "author_name"
            const val STATUS = "status"
        }
    }
}