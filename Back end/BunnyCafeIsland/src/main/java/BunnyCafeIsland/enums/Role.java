package BunnyCafeIsland.Enums;

import java.util.Set;

public enum Role {
    Staff(Set.of(Permission.STAFF_READ)),
    Manager(Set.of(
            Permission.MANAGER_CREATE,
            Permission.MANAGER_READ,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_UPDATE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions=permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
