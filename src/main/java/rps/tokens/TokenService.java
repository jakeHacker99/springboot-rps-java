package rps.tokens;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rps.repositories.TokenRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenService {

    TokenRepository tokenRepository;


    public Token creatToken() {
        Token token = Token.create();
        tokenRepository.save(token);
        return token;
    }

    public Token getTokenById(String tokenId){
        return tokenRepository.getOne(tokenId);

    }
}