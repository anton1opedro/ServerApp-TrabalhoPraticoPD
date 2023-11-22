package pt.isec.pd.as.pd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.isec.pd.as.pd.database.utilizadores.Users;
import pt.isec.pd.as.pd.database.utilizadores.UsersService;

@RestController
public class ClientController {

    @GetMapping
    public String getClient(String username){
        return "Hello World!";
    }

}