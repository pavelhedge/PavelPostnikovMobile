package setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TokenManager {

    private String token;

    TokenManager() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("token.txt"));
            token = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("No token provided");
        }
    }

    public String getToken() {
        return token;
    }
}


