import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " + this.age;
    }

    public static List<User> readUsersFromFile(String filePath) {
        List<User> users = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();//Reading one time to ignore the header
            while ((line = reader.readLine()) != null) {
                users.add(new User(line.split(" ")[0], Integer.valueOf(line.split(" ")[1])));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}


public class GsonPlayGround {
    public static void main(String[] args) {
        // Step 1: Define the user data as a text representation
        System.out.println("--===||| Defining the initial user data in text format |||===--");
        String usersToKeep = "Name age " +
                "\n" + "alice 21" +
                "\n" + "ruslan 33" +
                "\n" + "ryan 30";
        System.out.println("--===||| Content of 'users.txt': |||===--");
        System.out.println(usersToKeep);
        File usersTxt = new File("users.txt");
        File usersJson = new File("users.json");

        // Step 2: Initialize Gson and FileReaderAndWriter utilities
        System.out.println("--===||| Initializing Gson and FileReaderAndWriter |||===--");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileReaderAndWriter rw = new FileReaderAndWriter(); // A class from the first task

        // Step 3: Write the text representation of users to a file
        System.out.println("--===||| Writing user data to 'users.txt' |||===--");
        rw.writeToFile(usersToKeep, usersTxt.getPath());

        // Step 4: Read users from the file and parse them into a list
        System.out.println("--===||| Reading users from 'users.txt' |||===--");
        List<User> usersFromText = User.readUsersFromFile(usersTxt.getPath());
        System.out.println("--===||| Parsed user data from 'users.txt': |||===--");
        System.out.println(usersFromText);

        // Step 5: Serialize the list of users to JSON and write to a file
        System.out.println("--===||| Serializing user data to JSON and writing to 'users.json' |||===--");
        rw.writeToFile(gson.toJson(usersFromText), usersJson.getPath());

        // Step 6: Read the JSON data back from the file
        System.out.println("--===||| Reading JSON data from 'users.json' |||===--");
        StringBuilder sb;
        sb = rw.readAllTextFromFile(usersJson.getPath());
        System.out.println("--===||| Content of 'users.json': |||===--");
        System.out.println(sb);

        // Step 7: Deserialize the JSON data back into a list of users
        System.out.println("--===||| Deserializing JSON data into a list of users |||===--");
        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> usersRetrieved = gson.fromJson(sb.toString(), userListType);

        // Step 8: Output each user's details from the deserialized list
        System.out.println("--===||| Deserialized user data: |||===--");
        for (User u : usersRetrieved) {
            System.out.println(u.name + " " + u.age);
        }
    }
}
