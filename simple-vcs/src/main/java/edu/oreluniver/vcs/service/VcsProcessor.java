package edu.oreluniver.vcs.service;

import edu.oreluniver.vcs.model.Entry;
import edu.oreluniver.vcs.model.VcsObject;
import edu.oreluniver.vcs.model.VcsState;

import java.util.List;
import java.util.UUID;

/**
 * Class description
 */
public interface VcsProcessor<T> {

    void init(String initBranchName);
    void addEntry(T data);
    void addBranch(String branchName);

    List<String> getBranchesNames();

    void moveHead(UUID uuid);
    void moveHead(String branchName);

    List<Entry<T>> getEntries();

    VcsState getState();

    VcsObject getHead();
}
