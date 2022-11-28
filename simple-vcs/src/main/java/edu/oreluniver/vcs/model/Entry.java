package edu.oreluniver.vcs.model;

import java.util.UUID;

/**
 * Class description
 */
public class Entry<T> extends VcsObject {
    private final T data;
    private final UUID parentId;

    public Entry(T data, UUID parentId) {
        this.data = data;
        this.parentId = parentId;
    }

    public T getData() {
        return data;
    }

    public UUID getParentId() {
        return parentId;
    }
}
