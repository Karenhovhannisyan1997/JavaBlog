package storage;

import model.User;

public interface UserStorage {



    void add(User user);

    User getUserByEmailAndPassword(String email, String password);

    boolean isEmailUser(String email);
}
