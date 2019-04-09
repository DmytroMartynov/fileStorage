package dmytro.martynov;

import dmytro.martynov.Storage.PosgreSqlStorage;
import dmytro.martynov.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            PosgreSqlStorage sqlStorage = new PosgreSqlStorage();
            sqlStorage.removeAll();
            User user1 = new User("Maxim", 10);
            User user2 = new User("Sasha", 15);
            User user3 = new User("Masha", 21);
            User user4 = new User("Dasha", 26);
            sqlStorage.addUser(user1);
            sqlStorage.addUser(user2);
            sqlStorage.addUser(user3);
            sqlStorage.addUser(user4);
            user1.setName("Maxim Alekseev");
            sqlStorage.updateUser(user1);
            sqlStorage.removeUser(3);
            User user = sqlStorage.getUser(1);
            System.out.println(user);
            List<User> users = sqlStorage.getAllUsers();
            System.out.println(users);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
}
