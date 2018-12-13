package com.example.zoey.contact.helper;
public class ContactDBTable {

    public static String TABLE_NAME = "CONTACTS";

    public static String CONTACT_ID = "ID";
    public static String CONTACT_NAME = "NAME";
    public static String CONTACT_PHONE = "PHONE";

    public static String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTACT_NAME + " TEXT, "
            + CONTACT_PHONE + " TEXT);";

    public static String DELETE_CONTACTS_DATA = "DROP TABLE " + TABLE_NAME + ";";
    public static String INSERT_CONTACT = "INSERT INTO CONTACTS (" + CONTACT_NAME + "," + CONTACT_PHONE + ") values(?,?)";
    public static String UPDATE_CONTACT = "UPDATE "+ TABLE_NAME + " SET "+CONTACT_NAME+" = ?," + CONTACT_PHONE + " = ? WHERE " + CONTACT_ID  + " = ?";
    public static String DELETE_CONTACT = "DELETE FROM "+ TABLE_NAME + " WHERE " +  CONTACT_ID +" = ?";
    public static String QUERY_CONTACT = "SELECT * FROM "+ TABLE_NAME + " WHERE "+ CONTACT_NAME + " = ?";
    public static String QUERY_ALL_CONTACT = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + CONTACT_NAME + " ASC";
}

