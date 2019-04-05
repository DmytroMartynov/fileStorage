package dmytro.martynov;

public class Main {
    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage("resultData.txt");
        User user1 = new User("Alex",20);
        User user2 = new User("Dima",22);
        User user3 = new User("Anton",33);
        User user4 = new User("Jenya",19);


        fileStorage.addUser(user1);
        fileStorage.addUser(user2);
        fileStorage.addUser(user3);
        fileStorage.addUser(user4);
        fileStorage.removeUserByName("Alex");
        fileStorage.removeUser(2);
        fileStorage.updateUser(user1);
        //fileStorage.removeAll();
        System.out.println(fileStorage.getUser(3));
        System.out.println(fileStorage.getAllUsers());


    }
}
