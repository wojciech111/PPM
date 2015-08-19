package util;

/**
 * Created by Wojciech on 2015-08-19.
 */
public class LoginRequest {
    private String email;
    private String password;
    private String grantType;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password, String grantType) {
        this.email = email;
        this.password = password;
        this.grantType = grantType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
