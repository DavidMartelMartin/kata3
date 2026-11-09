package software.ulpgc.katas.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.katas.UserDto;
import software.ulpgc.katas.domain.model.User;
import software.ulpgc.katas.ports.UserStore;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

public class RandomUserStore implements UserStore {
    private final int size;

    public RandomUserStore(int size) {
        this.size = size;
    }

    @Override
    public List<User> load() {
        try {
            return loadFrom(new URI("https://randomuser.me/api?results=" + size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> loadFrom(URI uri) throws IOException, InterruptedException {
        return loadFrom(createRequestFor(uri));
    }

    private List<User> loadFrom(HttpRequest request) throws IOException, InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {
            return loadFrom(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
        }
    }

    private List<User> loadFrom(String json) {
        return loadFrom(new Gson().fromJson(json, JsonObject.class).get("results").getAsJsonArray());
    }

    private List<User> loadFrom(JsonArray array) {
        return loadFrom(array.asList());

    }

    private List<User> loadFrom(List<JsonElement> list) {
        return list.stream().map(i->toUser(i)).toList();
    }

    private User toUser(JsonElement element) {
        return toUser(new Gson().fromJson(element, UserDto.class));
    }

    private User toUser(UserDto dto) {
        return new User(
                dto.name.first + " " + dto.name.last,
                Instant.parse(dto.dob.date)
        );
    }

    private HttpRequest createRequestFor(URI uri) {
        return HttpRequest.newBuilder(uri).GET().build();
    }


}