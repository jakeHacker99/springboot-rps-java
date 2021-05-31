package rps.tokens;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@AllArgsConstructor
@CrossOrigin
public class TokenController {

    TokenService tokenService;

    @GetMapping("/new")
    public String creatToken(){
        return tokenService.creatToken().getId();
    }
}