package com.example.javafxaeroluxproject.services;

import java.sql.SQLException;
import java.util.List;

public interface IUser  <T> {
        void add (T t ) throws SQLException;
        void update(T t) throws SQLException;


        void delete (int id) throws SQLException;
        List<T> retrieve () throws SQLException;
public void updateUserPassword(String emailUser, String newPassword)throws  SQLException ;
}
