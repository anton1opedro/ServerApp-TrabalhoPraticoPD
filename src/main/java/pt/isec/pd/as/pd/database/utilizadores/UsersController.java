package pt.isec.pd.as.pd.database.utilizadores;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.as.pd.seguranca.TokenService;
import pt.isec.pd.as.pd.seguranca.UserAuthenticationProvider;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    private UsersService service;

    private TokenService tokenService;

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

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String loginUser(@RequestBody Users.UserDTO userDTO, HttpServletResponse response) {
        if (service.authenticateUser(userDTO.getUsername(), userDTO.getPassword())) {
            try{
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
                );
                String token = tokenService.generateToken(auth);
                return token;
            }catch(AuthenticationException e)
            {
                System.out.println("Error loging in");
            }
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

