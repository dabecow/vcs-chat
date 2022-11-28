package edu.oreluniver.vcs.repository;

import edu.oreluniver.vcs.model.VcsBranch;
import edu.oreluniver.vcs.model.VcsObject;

import java.util.HashMap;
import java.util.UUID;

public class VcsObjectsRepository {

    private final HashMap<UUID, VcsObject> objectByUuid = new HashMap<>();
    private final HashMap<String, VcsBranch> branchByName = new HashMap<>();

    public void addBranch(VcsBranch vcsBranch) {
        branchByName.put(vcsBranch.getName(), vcsBranch);
        addObjectToMap(vcsBranch);
    }

    public VcsBranch getBranchByName(String name) {
        return branchByName.get(name);
    }

    public void addObjectToMap(VcsObject vcsObject) {
        objectByUuid.put(vcsObject.getUuid(), vcsObject);
    }

    public VcsObject getObject(UUID uuid) {
        return objectByUuid.get(uuid);
    }

}
