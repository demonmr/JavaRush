package com.javarush.task.task36.task3608.model;



import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model{
   private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            users.add(i,new User(("A"+i),i,1));

        }
        this.modelData.setUsers(users);
    }

    @Override
    public void loadDeletedUsers () {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData (String name, long id, int level) {
        throw new UnsupportedOperationException();
    }

    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }
    public void deleteUserById(long userId) {
        throw new UnsupportedOperationException();
    }
}
