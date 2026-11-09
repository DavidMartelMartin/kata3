package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.ulpgc.katas.domain.model.User;
import software.ulpgc.katas.ports.UserWriter;

import java.time.Instant;
import java.util.List;

public class GsonUserWriter implements UserWriter {

    private static class JsonOutput {
        public final Metadata metadata;
        public final List<User> users;

        public JsonOutput(List<User> users) {
            this.users = users;
            this.metadata = new Metadata(users.size(), Instant.now().toString());
        }
    }

    private record Metadata(int totalRecords, String generatedAt) {}

    @Override
    public void write(List<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new JsonOutput(users));
        System.out.println(json);
    }
}