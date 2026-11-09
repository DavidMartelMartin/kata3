package software.ulpgc.katas.ports;

import software.ulpgc.katas.domain.model.User;
import java.util.List;

public interface UserStore {
    List<User> load();
}