package edu.oreluniver.vcs.service;

import edu.oreluniver.vcs.model.Entry;
import edu.oreluniver.vcs.model.VcsBranch;
import edu.oreluniver.vcs.model.VcsObject;
import edu.oreluniver.vcs.model.VcsState;
import edu.oreluniver.vcs.repository.VcsObjectsRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Class description
 */
public class VcsProcessorImpl<T> implements VcsProcessor<T> {

    private final VcsObjectsRepository vcsObjectsRepository = new VcsObjectsRepository();
    private VcsObject head;

    private VcsState state = VcsState.DETACHED;

    @Override
    public void init(String initBranchName) {
        addBranch(initBranchName);
    }

    @Override
    public void addEntry(T data) {
        Entry<T> entry;

        if (head == null || !(head instanceof VcsBranch)) {
            entry = new Entry<>(data, null);
        } else {
            var parentId = ((VcsBranch) head).getEntryUuid();
            entry = new Entry<>(data, parentId);
            ((VcsBranch) head).setEntryUuid(entry.getUuid());
        }

        vcsObjectsRepository.addObjectToMap(entry);
    }

    @Override
    public void addBranch(String branchName) {
        var branch = new VcsBranch(branchName);

        if (head instanceof VcsBranch) {
            branch.setEntryUuid(((VcsBranch) head).getEntryUuid());
        } else if (Objects.nonNull(head)){
            branch.setEntryUuid(head.getUuid());
        }

        vcsObjectsRepository.addBranch(branch);
        head = branch;
        state = VcsState.ATTACHED;
    }

    @Override
    public List<String> getBranchesNames() {
        return vcsObjectsRepository.getBranchesNames();
    }

    @Override
    public void moveHead(UUID uuid) {
        head = vcsObjectsRepository.getObject(uuid);
        if (head instanceof VcsBranch)
            state = VcsState.ATTACHED;
        else
            state = VcsState.DETACHED;
    }

    @Override
    public void moveHead(String branchName) {
        head = vcsObjectsRepository.getBranchByName(branchName);
        state = VcsState.ATTACHED;
    }

    @Override
    public List<Entry<T>> getEntries() {
        List<Entry<T>> list = new LinkedList<>();
        Entry<T> entry;
        if (state == VcsState.DETACHED)
            entry = (Entry<T>) head;
        else
            entry = (Entry<T>) vcsObjectsRepository.getObject(((VcsBranch) head).getEntryUuid());

        if (entry == null) return list;

        list.add(entry);
        UUID id = entry.getParentId();

        while (Objects.nonNull(id)) {
            entry = (Entry<T>) vcsObjectsRepository.getObject(id);
            list.add(entry);
            id = entry.getParentId();
        }

        return list;
    }

    @Override
    public VcsState getState() {
        return this.state;
    }

    public VcsObject getHead() {
        return this.head;
    }
}
