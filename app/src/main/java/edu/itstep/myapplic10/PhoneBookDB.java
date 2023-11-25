package edu.itstep.myapplic10;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Address.class}, version = 1)
public abstract class PhoneBookDB extends RoomDatabase {
    public abstract ContactDAO contactDAO();
    public abstract AddressDAO addressDAO();
}

