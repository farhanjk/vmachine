package com.vending.machine.domain;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Item DAO.
 */
@Dao
public interface ItemDao {

    @Query("select count(*) from item")
    int size();

    @Query("select * from item where code = :itemId")
    Item getItem(String itemId);

    @Query("select * from item order by code")
    List<Item> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM item")
    void deleteAll();

    @Query("DELETE FROM item where code = :itemId")
    void deleteById(String itemId);
}