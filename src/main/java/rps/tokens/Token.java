package rps.tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rps.model.game.Game;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "Token1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    public static Token create(){
        return new Token(UUID.randomUUID().toString(),null,null,null);}
    @Id
    String id;
   private String name;

   @OneToOne(cascade = CascadeType.ALL)
   Game ownerGame;
    @OneToOne(cascade = CascadeType.ALL)
    Game joinerGame;


}
