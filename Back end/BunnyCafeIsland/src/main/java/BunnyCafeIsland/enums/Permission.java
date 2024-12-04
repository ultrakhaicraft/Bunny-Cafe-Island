package BunnyCafeIsland.Enums;




public enum Permission {

    STAFF_READ("staff:read"),
    STAFF_CREATE("staff:create"),
    STAFF_UPDATE("staff:update"),
    STAFF_DELETE("staff:delete"),
    MANAGER_READ("manager:read"),
    MANAGER_CREATE("manager:create"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_DELETE("manager:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
