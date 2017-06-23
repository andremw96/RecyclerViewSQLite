package com.example.andre.recyclerviewsqlite;

/**
 * Created by andre on 23-Jun-17.
 */

public class Contants {
    //COLUMNS
    static final String ROW_ID = "id";
    static final String NAME = "name";
    static final String POSITION = "position";

    //DB PROPERTIES
    static final String DB_NAME = "a_DB";
    static final String TB_NAME = "a_TB";
    static final int DB_VERSION = '1';

    //CREATE TABLE
    static final String CREATE_TB = "CREATE TABLE a_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL, position TEXT NOT NULL);";

}
