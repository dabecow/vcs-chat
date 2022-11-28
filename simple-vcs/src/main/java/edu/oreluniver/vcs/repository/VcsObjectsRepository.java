package edu.oreluniver.vcs.repository;

import edu.oreluniver.vcs.model.VcsBranch;
import edu.oreluniver.vcs.model.VcsObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class VcsObjectsRepository {

    private final HashMap<UUID, VcsObject> objectByUuid = new HashMap<>();
    private final LinkedHashMap<String, VcsBranch> branchByName = new LinkedHashMap<>();

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

    public List<String> getBranchesNames() {
        return branchByName.values().stream().map(VcsBranch::getName).toList();
    }
}
