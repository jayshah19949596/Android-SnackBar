package com.example.jaysh.snackbar;

/**
 * Created by jaysh on 10/2/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class DBMgr {
    static final String DATABASE_NAME = "snackbar.db";
    static final int DATABASE_VERSION = 12;
    public static final int NAME_COLUMN = 1;
    public final static String USERPROFILE_TABLE = "userProfile";

    public final static String USERPROFILE_ID = "_id";

    public final static String USERPROFILE_EMAIL = "email";

    public final static String USERPROFILE_PASSWORD = "password";

    public final static String USERPROFILE_NAME = "name";

    public final static String USERPROFILE_ADDRESS = "address";

    public final static String USERPROFILE_CITY = "city";

    public final static String USERPROFILE_STATE = "state";

    public final static String USERPROFILE_ZIPCODE = "zipCode";

    public final static String USERPROFILE_COUNTRY = "country";

    public final static String USERPROFILE_NUMBER = "phoneNumber";

    public final static String USERPROFILE_ROLE = "role";

  /*=========================================================================*/


    public final static String MENU_TABLE = "menu";

    public final static String MENU_ID = "_id";

    public final static String MENU_CUISINETYPE = "cuisinetype";

    public final static String MENU_DISHES = "dishes";

    public final static String MENU_PRICE = "price";

    public final static String MENU_DESCRIPTION = "description";


    /*============================================================================*/


    public final static String ORDER_TABLE = "myOrder";

    public final static String ORDER_ID = "_id";

    public final static String ORDER_TIMESTAMP = "orderTimestamp";

    public final static String ORDER_USERNAME = "orderUsername";

    public final static String ORDER_DESCRIPTION = "orderDescription";

    public final static String ORDER_ESTIMATED_DELIVERY_TIME = "orderEstimatedDeliveryTime";


    public final static String ORDER_AMOUNT = "amount";

    public final static String ORDER_ADDRESS = "orderAddress";


    /*==============================================================================================*/

    static final String DATABASE_CREATE_USERPROFILE = "create table if not exists " + USERPROFILE_TABLE + " ( "
            + USERPROFILE_ID + " INTEGER primary key autoincrement,"
            + USERPROFILE_EMAIL +" text, "+USERPROFILE_NAME+ " text, "+USERPROFILE_PASSWORD+ " text," +
            USERPROFILE_ADDRESS+" text, "+USERPROFILE_CITY+" text, "+USERPROFILE_STATE+" text,"+USERPROFILE_ZIPCODE +" text, "
            +USERPROFILE_COUNTRY+" text, "+USERPROFILE_NUMBER +" text, "+USERPROFILE_ROLE+ " text);";

    static final String DATABASE_CREATE_MENU = "create table if not exists " + MENU_TABLE + " ( "
            + MENU_ID+ " INTEGER primary key autoincrement,"
            + MENU_CUISINETYPE+" text, "+MENU_DISHES+"  text, "+MENU_PRICE+" INTEGER,"+MENU_DESCRIPTION+" text );";

    static final String DATABASE_CREATE_ORDER = "create table if not exists " + ORDER_TABLE + " ( "
            + ORDER_ID  + " INTEGER primary key autoincrement,"
            + ORDER_TIMESTAMP +" text,"+ORDER_USERNAME+" text,"+ORDER_DESCRIPTION+ " text,"
            +ORDER_ESTIMATED_DELIVERY_TIME+" text,"+ORDER_ADDRESS+" text ,"+ORDER_AMOUNT+" INTEGER );";


    /*==============================================================================================*/

    public SQLiteDatabase db;
    private Context mContext;
    private DataBaseHelper dbHelper;
    private static DBMgr mUniqueInstance;

    public static DBMgr getInstance(Context context) {
        Log.d("DBMgr",DATABASE_CREATE_ORDER);

        if (mUniqueInstance == null) {
            mUniqueInstance = new DBMgr(context);
        }
        return mUniqueInstance;
    }

    private DBMgr(Context context) {
        mContext = context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        Log.d("Create Table Query ",DATABASE_CREATE_USERPROFILE);

        Log.d("DBMgr","ORDER table query  : "+DATABASE_CREATE_ORDER);


        String s = String.valueOf(Preference.getInstance(mContext).loadBooleanKey(Preference.IS_FIRST_TIME, false));

        Log.d("Preferenece Value",s) ;


        db = dbHelper.getWritableDatabase();
        if(!Preference.getInstance(mContext).loadBooleanKey(Preference.IS_FIRST_TIME, false)) {
            Log.d("DataBaseAdpater","Inside prefrence if");
            initialisingDataBase();

            Preference.getInstance(mContext).saveState(Preference.IS_FIRST_TIME, true);


        }
    }

    public void initialisingDataBase() {
        Log.d("getSinlgeUser","intialising data");
        ContentValues newValues1 = new ContentValues();
        newValues1.put(USERPROFILE_NAME, "JAY");
        newValues1.put(USERPROFILE_EMAIL, "JAY");
        newValues1.put(USERPROFILE_PASSWORD, "JAY");
        newValues1.put(USERPROFILE_ADDRESS, "Vintage Pads., 212 S.Cooper Street ");
        newValues1.put(USERPROFILE_CITY, "Arlington");
        newValues1.put(USERPROFILE_STATE,"TEXAS");
        newValues1.put(USERPROFILE_ZIPCODE, "76010");
        newValues1.put(USERPROFILE_COUNTRY, "USA");
        newValues1.put(USERPROFILE_NUMBER, "68943256");
        newValues1.put(USERPROFILE_ROLE, "ADMIN");
        db.insert(USERPROFILE_TABLE, null, newValues1);

        ContentValues newValues2 = new ContentValues();
        newValues2.put(MENU_CUISINETYPE, "SNACKS");
        newValues2.put(MENU_DISHES, "SAMOSA");
        newValues2.put(MENU_PRICE, 4);
        newValues2.put(MENU_DESCRIPTION, "A fried or baked dish with a savoury filling, such as spiced potatoes, " +
                "onions, peas, lentils, macaroni, noodles or minced meatA fried or baked dish with a " +
                "savoury filling, such as spiced potatoes, onions, peas, lentils, macaroni, noodles or minced meat");
        db.insert(MENU_TABLE, null, newValues2);

        ContentValues newValues3 = new ContentValues();
        newValues3.put(MENU_CUISINETYPE, "SNACKS");
        newValues3.put(MENU_DISHES, "PAAV BHAAJI");
        newValues3.put(MENU_PRICE, 5);
        newValues3.put(MENU_DESCRIPTION, " A thick vegetable curry usually prepared in butter " +
                "and served with a soft bread roll");
        db.insert(MENU_TABLE, null, newValues3);


        ContentValues newValues4 = new ContentValues();
        newValues4.put(MENU_CUISINETYPE, "SNACKS");
        newValues4.put(MENU_DISHES, "VADA PAV");
        newValues4.put(MENU_PRICE, 8);
        newValues4.put(MENU_DESCRIPTION, "A thick vegetable curry usually prepared in butter " +
                "and served with a soft bread roll");
        db.insert(MENU_TABLE, null, newValues4);


        ContentValues newValues5 = new ContentValues();
        newValues5.put(MENU_CUISINETYPE, "MUGHLAI");
        newValues5.put(MENU_DISHES, "SEEKH KEBAB");
        newValues5.put(MENU_PRICE, 14);
        newValues5.put(MENU_DESCRIPTION, "Prepared with minced meat with spices and " +
                "grilled on skewers and served with chutneys or mint sauce");
        db.insert(MENU_TABLE, null, newValues5);

        ContentValues newValues6 = new ContentValues();
        newValues6.put(MENU_CUISINETYPE, "MUGHLAI");
        newValues6.put(MENU_DISHES, "BIRYANI BADSHAHI");
        newValues6.put(MENU_PRICE, 18);
        newValues6.put(MENU_DESCRIPTION, " A mixed rice dish made with spices, rice," +
                " and meat, preferably mutton");
        db.insert(MENU_TABLE, null, newValues6);

        ContentValues newValues7 = new ContentValues();
        newValues7.put(MENU_CUISINETYPE, "MUGHLAI");
        newValues7.put(MENU_DISHES, "SHAHI KAJU AALU");
        newValues7.put(MENU_PRICE, 20);
        newValues7.put(MENU_DESCRIPTION, "A preparation of paneer in a thick" +
                " gravy made up of cream, tomatoes and spices");
        db.insert(MENU_TABLE, null, newValues7);


        ContentValues newValues8 = new ContentValues();
        newValues8.put(MENU_CUISINETYPE, "CHINESE");
        newValues8.put(MENU_DISHES, "CHOW MEIN");
        newValues8.put(MENU_PRICE, 21);
        newValues8.put(MENU_DESCRIPTION, "A popular dish combining noodles, vegetables, scrambled egg, ginger and garlic, soy\n" +
                "sauce, green chili sauce, red chili sauce and vinegar.");
        db.insert(MENU_TABLE, null, newValues8);

        ContentValues newValues9 = new ContentValues();
        newValues9.put(MENU_CUISINETYPE, "CHINESE");
        newValues9.put(MENU_DISHES, "MANCHURIAN");
        newValues9.put(MENU_PRICE, 19);
        newValues9.put(MENU_DESCRIPTION, "Small balls consisting of chicken with vegetables in a spicy sauce.");
        db.insert(MENU_TABLE, null, newValues9);

        ContentValues newValues10 = new ContentValues();
        newValues10.put(MENU_CUISINETYPE, "CHINESE");
        newValues10.put(MENU_DISHES, "FRIED RICE");
        newValues10.put(MENU_PRICE, 22);
        newValues10.put(MENU_DESCRIPTION, "Stir-fried steamed rice mixed with other ingredients " +
                "such as eggs, vegetables, and meat.");
        db.insert(MENU_TABLE, null, newValues10);

        ContentValues newValues11 = new ContentValues();
        newValues11.put(MENU_CUISINETYPE, "DESSERTS");
        newValues11.put(MENU_DISHES, "BLUEBERRY CHEESECAKE");
        newValues11.put(MENU_PRICE, 22);
        newValues11.put(MENU_DESCRIPTION, "A mixture of soft, cream cheese with graham crackers topped with" +
                "fruit, whipped cream, nuts, cookies, fruit sauce, and/or chocolate syrup..");
        db.insert(MENU_TABLE, null, newValues11);
        /*=============================
        * Inserting the CHEFS of SNACKBAR
        * */

        ContentValues newValues12 = new ContentValues();
        newValues12.put(USERPROFILE_NAME, "VINITH");
        newValues12.put(USERPROFILE_EMAIL, "VINITH");
        newValues12.put(USERPROFILE_PASSWORD, "VINITH");
        newValues12.put(USERPROFILE_ADDRESS, "KELLY APT., MITCHELL STREET ");
        newValues12.put(USERPROFILE_CITY, "Arlington");
        newValues12.put(USERPROFILE_STATE,"TEXAS");
        newValues12.put(USERPROFILE_ZIPCODE, "76010");
        newValues12.put(USERPROFILE_COUNTRY, "USA");
        newValues12.put(USERPROFILE_NUMBER, "689432576");
        newValues12.put(USERPROFILE_ROLE, "CHEF");
        db.insert(USERPROFILE_TABLE, null, newValues12);

        ContentValues newValues13 = new ContentValues();
        newValues13.put(USERPROFILE_NAME, "HITESH");
        newValues13.put(USERPROFILE_EMAIL, "HITESH");
        newValues13.put(USERPROFILE_PASSWORD, "HITESH");
        newValues13.put(USERPROFILE_ADDRESS, "VINTAGE APT., 212 South Cooper STREET ");
        newValues13.put(USERPROFILE_CITY, "Arlington");
        newValues13.put(USERPROFILE_STATE,"TEXAS");
        newValues13.put(USERPROFILE_ZIPCODE, "76013");
        newValues13.put(USERPROFILE_COUNTRY, "USA");
        newValues13.put(USERPROFILE_NUMBER, "673432576");
        newValues13.put(USERPROFILE_ROLE, "CHEF");
        db.insert(USERPROFILE_TABLE, null, newValues13);

         /*=============================
        * Inserting the ORDERS of SNACKBAR
        *   static final String DATABASE_CREATE_ORDER = "create table if not exists " + ORDER_TABLE + " ( "
            + ORDER_ID  + " INTEGER primary key autoincrement,"
            + ORDER_TIMESTAMP +" text,"+ORDER_USERNAME+" text,"+ORDER_DESCRIPTION+ " text,"
            +ORDER_ESTIMATED_DELIVERY_TIME+" text,"+ORDER_AMOUNT +" text)";
        * */

        ContentValues newValues14 = new ContentValues();
        newValues14.put(ORDER_TIMESTAMP, "02:35:16");
        newValues14.put(ORDER_USERNAME, "SRK");
        newValues14.put(ORDER_DESCRIPTION, "PAAVBHAAJI 4");
        newValues14.put(ORDER_ESTIMATED_DELIVERY_TIME, "03:05:16");
        newValues14.put(ORDER_ADDRESS,"VINTAGE APT., 212 South Cooper STREET,Arlington,TX-76013 ");
        newValues14.put(ORDER_AMOUNT, 18);


        db.insert(ORDER_TABLE, null, newValues14);

        ContentValues newValues15 = new ContentValues();
        newValues15.put(ORDER_TIMESTAMP, "01:00:16");
        newValues15.put(ORDER_USERNAME, "Salaman");
        newValues15.put(ORDER_DESCRIPTION, "VADAPAAV 3");
        newValues15.put(ORDER_ADDRESS,"University Oaks, 213 South Cooper STREET,Arlington,TX-76013 ");
        newValues15.put(ORDER_ESTIMATED_DELIVERY_TIME, "01:30:16");
        newValues15.put(ORDER_AMOUNT, 18);


        db.insert(ORDER_TABLE, null, newValues15);

        ContentValues newValues16 = new ContentValues();
        newValues16.put(ORDER_TIMESTAMP, "01:00:16");
        newValues16.put(ORDER_USERNAME, "Ajay");
        newValues16.put(ORDER_DESCRIPTION, "SAMOSA 2");
        newValues16.put(ORDER_ADDRESS,"CooperChase Apt, 215 North Cooper STREET,Arlington,TX-76013 ");
        newValues16.put(ORDER_ESTIMATED_DELIVERY_TIME, "01:30:16");
        newValues16.put(ORDER_AMOUNT, 18);


        db.insert(ORDER_TABLE, null, newValues16);


        ContentValues newValues17 = new ContentValues();
        newValues17.put(ORDER_TIMESTAMP, "01:00:16");
        newValues17.put(ORDER_USERNAME, "Salaman");
        newValues17.put(ORDER_DESCRIPTION, "BIRYANIBADSHAHI 3");
        newValues17.put(ORDER_ADDRESS,"CooperChase Apt, 215 North Cooper STREET,Arlington,TX-76013 ");
        newValues17.put(ORDER_ESTIMATED_DELIVERY_TIME, "01:30:16");
        newValues17.put(ORDER_AMOUNT, 18);
        db.insert(ORDER_TABLE, null, newValues17);


        ContentValues newValues18 = new ContentValues();
        newValues18.put(ORDER_TIMESTAMP, "01:00:16");
        newValues18.put(ORDER_USERNAME, "SRK");
        newValues18.put(ORDER_DESCRIPTION, "BIRYANIBADSHAHI 2");
        newValues18.put(ORDER_ADDRESS,"SouthCampus Apt, 230 Mitchell STREET,Arlington,TX-76013 ");

        newValues18.put(ORDER_ESTIMATED_DELIVERY_TIME, "01:30:16");
        newValues18.put(ORDER_AMOUNT, 18);


        db.insert(ORDER_TABLE, null, newValues18);



        /*=============================
        * Inserting the customer of SNACKBAR
        *
        * */
        ContentValues newValues20 = new ContentValues();
        newValues20.put(USERPROFILE_NAME, "SRK");
        newValues20.put(USERPROFILE_EMAIL, "SRK");
        newValues20.put(USERPROFILE_PASSWORD, "SRK");
        newValues20.put(USERPROFILE_ADDRESS, "Vintage Pads., 212 S.Cooper Street ");
        newValues20.put(USERPROFILE_CITY, "Arlington");
        newValues20.put(USERPROFILE_STATE,"TEXAS");
        newValues20.put(USERPROFILE_ZIPCODE, "76010");
        newValues20.put(USERPROFILE_COUNTRY, "USA");
        newValues20.put(USERPROFILE_NUMBER, "68943256");
        newValues20.put(USERPROFILE_ROLE, "CUSTOMER");
        db.insert(USERPROFILE_TABLE, null, newValues20);


        ContentValues newValues21 = new ContentValues();
        newValues21.put(USERPROFILE_NAME, "Salman");
        newValues21.put(USERPROFILE_EMAIL, "Salman");
        newValues21.put(USERPROFILE_PASSWORD, "Salman");
        newValues21.put(USERPROFILE_ADDRESS, "Vintage Pads., 212 S.Cooper Street ");
        newValues21.put(USERPROFILE_CITY, "Arlington");
        newValues21.put(USERPROFILE_STATE,"TEXAS");
        newValues21.put(USERPROFILE_ZIPCODE, "76010");
        newValues21.put(USERPROFILE_COUNTRY, "USA");
        newValues21.put(USERPROFILE_NUMBER, "68943256");
        newValues21.put(USERPROFILE_ROLE, "CUSTOMER");
        db.insert(USERPROFILE_TABLE, null, newValues21);



        ContentValues newValues22 = new ContentValues();
        newValues22.put(USERPROFILE_NAME, "Ajay");
        newValues22.put(USERPROFILE_EMAIL, "Ajay");
        newValues22.put(USERPROFILE_PASSWORD, "Ajay");
        newValues22.put(USERPROFILE_ADDRESS, "Vintage Pads., 212 S.Cooper Street ");
        newValues22.put(USERPROFILE_CITY, "Arlington");
        newValues22.put(USERPROFILE_STATE,"TEXAS");
        newValues22.put(USERPROFILE_ZIPCODE, "76010");
        newValues22.put(USERPROFILE_COUNTRY, "USA");
        newValues22.put(USERPROFILE_NUMBER, "68943256");
        newValues22.put(USERPROFILE_ROLE, "CUSTOMER");
        db.insert(USERPROFILE_TABLE, null, newValues22);



    }

    public DBMgr open() throws SQLException {
        return this;
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }


    /* THIS FUNCTION WILL INSERT USER FROM SIGNUP THE DATABASE */
    public void insertEntry(String userName, String userId, String password, String address,
                            String city, String zipCode, String country,String number, String role) {


        ContentValues newValues = new ContentValues();
        newValues.put(USERPROFILE_NAME, userName);
        newValues.put(USERPROFILE_EMAIL, userId);
        newValues.put(USERPROFILE_PASSWORD, password);
        newValues.put(USERPROFILE_ADDRESS, address);
        newValues.put(USERPROFILE_CITY, city);
        newValues.put(USERPROFILE_ZIPCODE, zipCode);
        newValues.put(USERPROFILE_COUNTRY, country);
        newValues.put(USERPROFILE_NUMBER, number);
        newValues.put(USERPROFILE_ROLE, role);
        db.insert(USERPROFILE_TABLE, null, newValues);

    }

    /*==============================================================*/
    public void deleteUSER(String UserName) {

        String where = USERPROFILE_NAME+"=?";
        db.delete(USERPROFILE_TABLE, where,
                new String[]{UserName});
        return ;
    }

    /*==============================================================*/
    public String getPassword(String emailID) {
        Cursor cursor = db.query(USERPROFILE_TABLE, null, USERPROFILE_EMAIL+"=?",
                new String[]{emailID}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String Row = cursor.getString(cursor.getColumnIndex(USERPROFILE_PASSWORD));
        cursor.close();
        return Row;
    }

    /*======================================================*/
    public Cursor getSinlgeUser(String emailID) {
        Cursor cursor = db.query("userProfile", null, "email =?",
                new String[]{emailID}, null, null, null);




        /*if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }*/
        cursor.moveToFirst();
        /*String Row = cursor.getString(cursor.getColumnIndex(USERPROFILE_NAME))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_EMAIL))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_PASSWORD))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_ADDRESS))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_CITY))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_STATE))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_COUNTRY))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_NUMBER))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_ROLE)) ;*/
        //cursor.close();
        return cursor;
    }



    /*===================================================*/

    public void updateUSer(String userID, String userName,String password, String address, String city, String zipcode,
                           String country, String number) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(USERPROFILE_NAME, userName);
        updatedValues.put(USERPROFILE_PASSWORD, password);
        updatedValues.put(USERPROFILE_ADDRESS, address);
        updatedValues.put(USERPROFILE_CITY, city);
        updatedValues.put(USERPROFILE_ZIPCODE, zipcode);
        updatedValues.put(USERPROFILE_COUNTRY, country);
        updatedValues.put(USERPROFILE_NUMBER, number);



        String where = USERPROFILE_EMAIL +" = ?";
        db.update(USERPROFILE_TABLE, updatedValues, where, new String[]{userID});
    }


    /*===================================================*/

    public Cursor getMenu() {

        String orderBy =  MENU_CUISINETYPE + " DESC";

        Cursor cursor = db.query(MENU_TABLE, null, null ,
                null , null, null, orderBy);
        cursor.moveToFirst();
        String s = String.valueOf(cursor);
        Log.d("Cursor query value",cursor.getString(cursor.getColumnIndex(MENU_DESCRIPTION)));


        //MENUID" + " INTEGER primary key autoincrement,"
        //+ "CUISINETYPE text,DISHES  text,PRICE INTEGER,DESCRIPTION text)";

        /*cursor.moveToFirst();
        String  Row= cursor.getString(cursor.getColumnIndex("MENUID"))+" "+cursor.getString(cursor.getColumnIndex("USERID"))
                +" "+cursor.getString(cursor.getColumnIndex("PASSWORD"))+" "+cursor.getString(cursor.getColumnIndex("ADDRESS"))
                +" "+cursor.getString(cursor.getColumnIndex("STREETNAME"))+" "+cursor.getString(cursor.getColumnIndex("CITY"))
                +" "+cursor.getString(cursor.getColumnIndex("ZIPCODE"))+" "+cursor.getString(cursor.getColumnIndex("COUNTRY"));
        cursor.close();*/

        return cursor;
    }

    public void updateDish(String dishName, String cuisineType, String price,String description) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(MENU_CUISINETYPE, cuisineType);
        updatedValues.put(MENU_PRICE, price);
        updatedValues.put(MENU_DESCRIPTION, description);


        String where = MENU_DISHES +" = ?";
        db.update(MENU_TABLE, updatedValues, where, new String[]{dishName});
    }


    public void addDishToMenu(String dishName , String cuisineType, String description, String price){

        ContentValues newValues9 = new ContentValues();
        newValues9.put(MENU_CUISINETYPE, cuisineType);
        newValues9.put(MENU_DISHES, dishName);
        newValues9.put(MENU_PRICE, price);
        newValues9.put(MENU_DESCRIPTION, description);
        db.insert(MENU_TABLE, null, newValues9);

    }

    public void deleteDish(String dishName) {

        String where = MENU_DISHES+"=?";
        db.delete(MENU_TABLE, where,
                new String[]{dishName});
        return ;
    }



    public Cursor getSingleMenu(String dishName) {


        Cursor cursor = db.query(MENU_TABLE , null, MENU_DISHES+" =?",
                new String[]{dishName}, null, null, null);

        cursor.moveToFirst();
        String s = String.valueOf(cursor);




        return cursor;
    }
    /*============================================================*/


    public Cursor getOrder() {
        Log.d("Cursor query value","Inside cursor");

        String orderBy =  ORDER_ID + " ASC";

        Cursor cursor = db.query(ORDER_TABLE, null, null ,
                null , null, null, orderBy);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            Log.d("Cursor query value",cursor.getString(cursor.getColumnIndex(ORDER_ID)));
            cursor.moveToNext();
        }

        //Log.d("Cursor query value",cursor.getString(cursor.getColumnIndex(ORDER_ID)));

        cursor.moveToFirst();

        return cursor;
    }


    public Cursor getSingleOrder(String orderId) {
        Log.d("Cursor query value","Inside cursor");


        Cursor cursor = db.query(ORDER_TABLE, null, ORDER_ID+" =?",
                new String[]{orderId}, null, null, null);

        cursor.moveToFirst();
        return cursor;
    }



 /*============================================================*/

    public void insertOrder(){

        String Description="";

        for(Iterator<Cart> it = Order.mCartItems.iterator(); it.hasNext();) {
            Cart crt = it.next();

            Description = Description+crt.getDishName().replaceAll(" ","")+" "+crt.getDishQuantity()+" ";
            //Log.d("DBMgr",String.valueOf(crt.getDishName()) );
            Log.d("DBMgr",String.valueOf(crt.getDishQuantity()) );
        }



        Log.d("DBMgr","Description"+Description );




        ContentValues newValues18 = new ContentValues();
        newValues18.put(ORDER_TIMESTAMP, Order.mTimeStamp);
        newValues18.put(ORDER_USERNAME, Order.mUsername);
        newValues18.put(ORDER_DESCRIPTION,Description);
        newValues18.put(ORDER_ESTIMATED_DELIVERY_TIME, Order.mEstimatedDeliveryTime);
        newValues18.put(ORDER_AMOUNT, Order.mTotalPrice);
        newValues18.put(ORDER_ADDRESS,Order.mAddress);
        db.insert(ORDER_TABLE, null, newValues18);


    }

    public void removeOrder(String orderId)
    {

        String where = ORDER_ID+"=?";
        db.delete(ORDER_TABLE, where, new String[]{orderId});
       // return db.delete(ORDER_ID, ORDER_ID + "=" + orderId, null) > 0;

    }



    }



