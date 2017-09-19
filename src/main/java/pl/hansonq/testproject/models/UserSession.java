package pl.hansonq.testproject.models;


public class UserSession {  //
    // Sigleton sprawdzający czy user jest zalogowany
    private static UserSession ourInstance = new UserSession();

    public static UserSession getInstance() {
        return ourInstance;
    }

    private UserSession() {
    }
    private int id;
    private String username;
    private boolean isLogedIn;

    public int getId() {
        return id;
    }

    public UserSession setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserSession setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLogedIn() {
        return isLogedIn;
    }

    public UserSession setLogedIn(boolean logedIn) {
        isLogedIn = logedIn;
        return this;
    }
}
