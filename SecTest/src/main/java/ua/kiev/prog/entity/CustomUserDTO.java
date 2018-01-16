package ua.kiev.prog.entity;

public class CustomUserDTO {
    private long id;
    private String login;
    private String role;

    public CustomUserDTO(long id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    @Override
    public String toString() {
        return "CustomUserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }
}
