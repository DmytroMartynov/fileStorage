package dmytro.martynov;

public class Main {
    public static void main(String[] args) {
        FileStorage file = new FileStorage("resultData.txt");
        User user1 = new User("Alex", 20);
        User user2 = new User("Dima", 22);
        User user3 = new User("Anton", 33);
        User user4 = new User("Jenya", 19);
        file.addUser(user1);
        file.addUser(user2);
        file.addUser(user3);
        file.addUser(user4);
        file.removeUserByName("Alex");
        file.removeUser(2);
        file.updateUser(user1);
        //fileStorage.removeAll();
        System.out.println(file.getUser(3));
        System.out.println(file.getAllUsers());


    }
}
