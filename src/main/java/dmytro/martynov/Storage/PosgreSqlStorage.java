package dmytro.martynov.Storage;

import dmytro.martynov.model.Storage;
import dmytro.martynov.model.User;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PosgreSqlStorage implements Storage {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;
    private int id = 0;


    public PosgreSqlStorage() throws SQLException, IOException {
        FileInputStream inputStream = new FileInputStream("password.txt");
        String everything = IOUtils.toString(inputStream);
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/users", "postgres", everything);
        createUsersTable();
    }

    private void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "_id int PRIMARY KEY,\n" +
                    "name varchar(30),\n" +
                    "age int\n" +
                    ");");
        }
    }

    public void removeAll() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE _id = '" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserByName(String name) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE name = '" + name + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {

        user.setId(id++);
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO users VALUES ('%d', '%s', '%d');", user.getId(), user.getName(), user.getAge());
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("UPDATE users SET name = '%s', age = '%d' WHERE _id = '%d'", user.getName(), user.getAge(), user.getId());
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id) {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM users WHERE _id = '%d';", id);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                int userId = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new User(userId, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List< User > getAllUsers() {
        List< User > users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                int userId = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                users.add(new User(userId, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
