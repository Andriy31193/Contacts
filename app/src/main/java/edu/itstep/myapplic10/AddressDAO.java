package edu.itstep.myapplic10;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface AddressDAO {
    @Insert
    long insert(Address address);

}

