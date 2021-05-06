package rps.tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rps.model.game.Game;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    public static Token create(){
        return new Token(UUID.randomUUID().toString(),null,null,null);}
    @Id
    String id;
   private String name;

   Game ownerGame;
   Game joinerGame;


}
