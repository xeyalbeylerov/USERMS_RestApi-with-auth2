/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.User;

import java.util.List;

/**
 * @author xeyal
 */
public interface UserServiceInter {

    List<User> getAll(String name, String surname);

    List<User> getAll(String name, String surname,Integer nationalityid);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
    boolean isEmailExists(String email);
    boolean isIdExists(Integer id);
    User getById(int userId);

    User addUser(User u);

    User updateUser(User u);

    boolean removeUser(int i);



}
