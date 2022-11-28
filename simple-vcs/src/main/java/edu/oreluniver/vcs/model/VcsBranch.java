package edu.oreluniver.vcs.model;

import java.util.UUID;

/**
 * Class description
 */
public class VcsBranch extends VcsObject {

    private final String name;
    private UUID entryUuid;

    public VcsBranch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getEntryUuid() {
        return entryUuid;
    }

    public void setEntryUuid(UUID entryUuid) {
        this.entryUuid = entryUuid;
    }
}
