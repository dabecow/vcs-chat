package edu.oreluniver.vcs.model;

import java.util.UUID;

/**
 * Class description
 */
public class VcsObject {
    private final UUID uuid = UUID.randomUUID();

    public VcsObject() {}

    public UUID getUuid() {
        return uuid;
    }
}
