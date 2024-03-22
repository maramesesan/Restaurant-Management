package Tables;

public class StaffRole {

    private int role_code;
    private String description;

    public StaffRole(int role_code, String description) {
        this.role_code = role_code;
        this.description = description;
    }

    public int getRole_code() {
        return role_code;
    }

    public String getDescription() {
        return description;
    }
}
