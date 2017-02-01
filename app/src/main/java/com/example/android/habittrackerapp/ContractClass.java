package com.example.android.habittrackerapp;

import android.provider.BaseColumns;

/**
 * Created by fuguBook on 13/7/16.
 *
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same package.
 * This lets you change a column name in one place and have it propagate throughout your code.
 * A good way to organize a contract class is to put definitions that are global to your
 * whole database in the root level of the class.
 *
 * Then create an inner class for each table that enumerates its columns.
 *
 */
public class ContractClass {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ContractClass() {}

    // Inner class that defines the table contents
    public static abstract class innerClass implements BaseColumns {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "databaseName.db";
        public static final String TABLE_NAME = "name";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
    }
}
