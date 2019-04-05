package dmytro.martynov;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileStorage implements Storage {
    private String fileName;
    private int counter = 1;
    private List< User > usersList = new ArrayList< User >();

    public FileStorage(String fileName) {
        this.fileName = fileName;
    }

    private void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(usersList);
        try {
            FileUtils.writeStringToFile(new File(fileName), json, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeAll() {
        usersList.clear();
        saveToFile();
    }

    public void removeUser(int id) {

        for (User user : usersList) {
            if (user.getId() == id) {
                usersList.remove(user);
                break;
            }
        }
        saveToFile();

    }

    public void removeUserByName(String name) {
        for (User user : usersList) {
            if (user.getName().equals(name)) {
                usersList.remove(user);
                break;
            }
        }
        saveToFile();
    }

    public void addUser(User user) {
        user.setId(counter);
        counter++;
        usersList.add(user);
        saveToFile();
    }

    public void updateUser(User user) {
        for (User users : usersList) {
            if (users.getId() == user.getId()) {
                users = user;
                break;
            }
        }
        saveToFile();
    }

    public User getUser(int id) {
        for (User user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List< User > getAllUsers() {
        return Collections.unmodifiableList(usersList);
    }
}
