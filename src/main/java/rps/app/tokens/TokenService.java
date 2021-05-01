package rps.app.tokens;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    Map<String, Token> tokens = new HashMap<>();

    public Token creatToken() {
        Token token = Token.create();
        tokens.put(token.getId(),token);
        return token;
    }

    public Token getTokenById(String tokenId){
        return tokens.get(tokenId);

    }
}

