package edu.itstep.myapplic10;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static App app;
    private PhoneBookDB phoneBookDB;
    private ContactDAO contactDAO;
    private AddressDAO addressDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        phoneBookDB = Room.databaseBuilder(
                this,
                PhoneBookDB.class,
                "PhoneBook"
        ).allowMainThreadQueries().build();
        contactDAO = phoneBookDB.contactDAO();
        addressDAO = phoneBookDB.addressDAO();
    }

    public static App getInstance() {
        return app;
    }

    public ContactDAO getContactDAO(){
        return contactDAO;
    }
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }
}