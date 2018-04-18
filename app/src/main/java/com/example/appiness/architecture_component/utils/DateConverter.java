package com.example.appiness.architecture_component.utils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by appiness on 2/3/18.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
