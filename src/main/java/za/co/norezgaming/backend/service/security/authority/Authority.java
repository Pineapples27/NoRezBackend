package za.co.norezgaming.backend.service.security.authority;

public enum Authority {
    STANDARD_USER("STANDARD_USER"),
    ELEVATED_USER("ELEVATED_USER");

    private String auth;

    Authority(String auth) {
        this.auth = auth;
    }

    public String getValue() {
        return auth;
    }
}
