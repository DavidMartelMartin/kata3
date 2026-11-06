package software.ulpgc.katas;

import software.ulpgc.katas.adapters.GsonUserWriter;
import software.ulpgc.katas.adapters.RandomUserStore;
import software.ulpgc.katas.domain.model.User;
import software.ulpgc.katas.ports.UserStore;
import software.ulpgc.katas.ports.UserWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserStore userStore = new RandomUserStore(20);
        UserWriter userWriter = new GsonUserWriter();

        List<User> users = userStore.load();
        userWriter.write(users);
    }
}