package storage.impl;

import model.User;
import storage.UserStorage;

public class UserStorageImpl implements UserStorage {

    private User[] users = new User[16];
    private int size = 0;

    @Override
    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        if (user.getName().length() < 3 || user.getSurname().length() < 3) {
            System.out.println("user name or surname <3  please try again");
        }

        if (user.getEmail().length() < 6 || user.getPassword().length() < 6){
            System.out.println("user email or password <6 please try again");
        }
            users[size++] = user;
    }

    private void extend() {
        User[] array = new User[size + 32];
        System.arraycopy(users, 0, array, 0, users.length);
        users = array;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public boolean isEmailUser(String email) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
