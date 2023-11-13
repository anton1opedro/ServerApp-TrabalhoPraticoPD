package pt.isec.pd.as.pd.database.utilizadores;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    private UsersService service;

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public String getAllUsers(){
        List<Users> usersList = service.listAllUsers();
        return usersList.toString();
    }

    @PostMapping("/signup")
    public String signUpUser(@RequestBody Users.UserDTO userDTO) {
        String signUpResult = service.addUser(userDTO.getNome(), userDTO.getUsername(), userDTO.getPassword());
        return signUpResult;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users.UserDTO userDTO) {
        if (service.authenticateUser(userDTO.getUsername(), userDTO.getPassword())) {
            return "Login Successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam String oldUsername, @RequestBody Users.UserDTO userDTO) {
        String updateResult = service.updateUser(oldUsername, userDTO.getNome(), userDTO.getUsername(), userDTO.getPassword());
        return updateResult;
    }

    @GetMapping("/")
    public String getUserByNome(@RequestParam String nome) {
        String updateResult = "nome";
        return updateResult;
    }
}

