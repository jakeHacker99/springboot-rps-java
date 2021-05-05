package rps.tokens;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Token {
    public static Token create(){
        return new Token(UUID.randomUUID().toString());}
    String id;
}
