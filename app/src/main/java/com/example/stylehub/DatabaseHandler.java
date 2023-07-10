package com.example.stylehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "StyleHUB";

    private static final String TABLE_USERS = "Users";

    private static final String KEY_ID_USER = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAMA = "nama";

    private static final String TABLE_PRODUCTS = "Products";
    private static final String KEY_ID_PRODUCT = "id";
    private static final String KEY_PRODUCT_NAME = "name";
    private static final String KEY_PRICE = "price";

    private static final String TABLE_TRANSACTION = "Transactions";
    private static final String KEY_ID_TRANSACTION = "id";
    private static final String KEY_PRODUCT_ID = "product_id";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_TRANSACTION_DATE = "transaction_date";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                KEY_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_USERNAME + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_NAMA + " TEXT)";
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                KEY_ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_PRODUCT_NAME + " TEXT,"+
                KEY_PRICE + " INTEGER)";
        String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + TABLE_TRANSACTION + "(" +
                KEY_ID_TRANSACTION + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_PRODUCT_ID + " INTEGER," +
                KEY_USER_ID + " INTEGER," +
                KEY_TRANSACTION_DATE + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        onCreate(db);
    }

    public long addRecordUserAccount(User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NAMA, user.getNama());

        if(db.insert(TABLE_USERS, null, values) == -1){
            db.close();
            return -1;
        }
        db.close();
        return 1;
    }

    public User getUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USERNAME + " ='" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        User user = null;
        if(cursor.moveToFirst()){
            user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setNama(cursor.getString(3));
        }
        cursor.close();
        return user;
    }

    public boolean deleteUser(Integer userId){
        SQLiteDatabase db = getReadableDatabase();

        db.delete(TABLE_TRANSACTION, KEY_USER_ID + "=?", new String[]{userId.toString()});
        return db.delete(TABLE_USERS, KEY_ID_USER + "=?", new String[]{userId.toString()}) > 0;
    }

    public boolean updatePassword(Integer userId, String newPassword){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, newPassword);
        return db.update(TABLE_USERS, values, KEY_ID_USER + "=?", new String[]{userId.toString()}) > 0;
    }

    public long addRecordProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, product.getName());
        values.put(KEY_PRICE, product.getPrice());

        if(db.insert(TABLE_PRODUCTS, null, values) == -1){
            db.close();
            return -1;
        }
        db.close();
        return 1;
    }

    public long addRecordTransaction(Transaction transaction){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, transaction.getIdProduct());
        values.put(KEY_USER_ID, transaction.getIdUser());
        values.put(KEY_TRANSACTION_DATE, transaction.getTransactionDate());

        if(db.insert(TABLE_TRANSACTION, null, values) == -1){
            db.close();
            return -1;
        }
        db.close();
        return 1;
    }

    public Product getProduct(int productId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_ID_PRODUCT + " ='" + productId + "'";
        Cursor cursor = db.rawQuery(query, null);

        Product product = null;
        if (cursor.moveToFirst()){
            product = new Product();
            product.setIdProduct(Integer.parseInt(cursor.getString(0)));
            product.setName(cursor.getString(1));
            product.setPrice(Integer.parseInt(cursor.getString(2)));
        }
        cursor.close();
        return product;
    }

    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> arrProduct = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                arrProduct.add(new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)
                ));
            } while (cursor.moveToNext());
        }
        return arrProduct;
    }

    public ArrayList<Transaction> getTransaction(int userId){
        ArrayList<Transaction> arrTransaction = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE " + KEY_USER_ID + " = " + userId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                arrTransaction.add(new Transaction(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        return arrTransaction;
    }
}
