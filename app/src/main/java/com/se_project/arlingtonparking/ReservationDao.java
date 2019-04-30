package com.se_project.arlingtonparking;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ReservationDao {
    @Query("SELECT * FROM reservation")
    List<Reservation> getAll();

    @Query("SELECT * FROM reservation WHERE uid IN (:ids)")
    List<Reservation> loadAllByIds(int[] ids);

    @Query("SELECT * FROM reservation WHERE uid LIKE :uid LIMIT 1")
    Reservation getReservation(int uid);

    @Query("SELECT * FROM reservation WHERE type LIKE :type AND reserved LIKE :options")
    List<Reservation> getReservationType(int type, boolean options);

    @Query("SELECT * FROM reservation WHERE username LIKE :username")
    List<Reservation> getReservationUser(String username);

    @Query("SELECT * FROM reservation WHERE username LIKE :username AND no_show LIKE:noshow OR overdue LIKE:overdue")
    List<Reservation> getReservationNoshow(String username, boolean noshow, boolean overdue);

    @Insert
    void insert(Reservation... reservations);

    @Update
    void update(Reservation... reservations);

    @Delete
    void delete(Reservation reservation);
}
