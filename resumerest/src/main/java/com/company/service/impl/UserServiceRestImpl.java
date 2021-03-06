/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;


import com.company.exceptions.IdIsNullException;
import com.company.exceptions.userExceptions.UserNotFoundException;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import com.company.service.inter.UserServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xeyal
 */


@Service(value = "userServiceRest")

public class UserServiceRestImpl implements UserServiceRestInter {


    @Autowired
    @Qualifier("userService")
    private UserServiceInter userDao;

    @Override
    public List<User> getAll(String name, String surname) {
        return userDao.getAll(name, surname);
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean removeUser(int id) throws UserNotFoundException {
        if(!userDao.isIdExists(id))throw new UserNotFoundException();
        userDao.removeUser(id);
        return true;
    }


    @Override
    public UserDTO updateUser(UserDTO userDTO) throws IdIsNullException, UserNotFoundException {
        Integer id = userDTO.getId();
        //check id null
        if (id == null) throw new IdIsNullException();
        //check user is exists
        if (!userDao.isIdExists(id)) throw new UserNotFoundException();

        User user = userDao.getById(id);
        //update users parameter
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        String password = bcrypt.encode(userDTO.getPassword());
        user.setPassword(password);
        userDao.updateUser(user);
        return userDTO;
    }


    @Override
    public User getById(int id) throws UserNotFoundException {
        if (!userDao.isIdExists(id)) throw new UserNotFoundException();
        User user = userDao.getById(id);
        return user;
    }



    @Autowired
    BCryptPasswordEncoder bcrypt;


    @Override
    public UserDTO addUser(UserDTO userDto) throws Exception {

        boolean isExists = userDao.isEmailExists(userDto.getEmail());
        if (isExists == true) {
            throw new Exception();
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        String password = bcrypt.encode(userDto.getPassword());
        user.setPassword(password);

        User userD = userDao.addUser(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userD.getId());
        userDTO.setName(userD.getName());
        userDTO.setSurname(userD.getSurname());
        userDTO.setEmail(userD.getEmail());
        userDTO.setPassword(userD.getPassword());
        return userDTO;
    }


}
