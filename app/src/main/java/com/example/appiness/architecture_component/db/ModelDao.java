package com.example.appiness.architecture_component.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.example.appiness.architecture_component.models.EntityModel;
import com.example.appiness.architecture_component.utils.DateConverter;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by appiness on 2/3/18.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface ModelDao {

    @Query("select * from EntityModel")
    LiveData<List<EntityModel>> getAllBorrowedItems();

    @Query("select * from EntityModel where id = :id")
    EntityModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(EntityModel entityModel);

    @Delete
    void deleteBorrow(EntityModel entityModel);

}
