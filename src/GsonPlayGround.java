import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class GsonPlayGround {
    public static void main(String[] args) {

        User[] usersToKeep = new User[]{new User("alice", 21),
                new User("ryan", 30)};

        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        FileReaderAndWriter rw = new FileReaderAndWriter();//a class from the first task, came in handy😁

        sb.append(gson.toJson(usersToKeep));
        rw.writeToFile(sb.toString(), "users.json");
        sb = rw.readAllTextFromFile("users.json");

        Type userListType = new TypeToken<List<User>>() {
        }.getType();
        List<User> usersRetrieved = gson.fromJson(sb.toString(), userListType);

        for (User u : usersRetrieved) {
            System.out.println(u.name + " " + u.age);
        }
    }
}