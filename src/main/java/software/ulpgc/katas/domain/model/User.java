package software.ulpgc.katas.domain.model;

import java.time.Instant;

public record User(String name, Instant birthday) {
}