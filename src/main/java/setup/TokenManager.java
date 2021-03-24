package setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TokenManager {

    private String token;

    TokenManager() {
        try {
            token = new BufferedReader(new FileReader("token.txt")).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("No token provided");
        }
    }

    public String getToken() {
        return token;
    }
}


