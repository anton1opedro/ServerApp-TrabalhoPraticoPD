package pt.isec.pd.as.pd.database.utilizadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService
{
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> listAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    public Users create(Users client) {
        return usersRepository.save(client);
    }

    public boolean findUser(String username, String password) {
        Users user = usersRepository.findByUsername(username);
        return user != null;
    }

    public String addUser(String nome, String username, String password) {
        Optional<Users> existingUserByUsername = Optional.ofNullable(usersRepository.findByUsername(username));
        Optional<Users> existingByName = Optional.ofNullable(usersRepository.findByNome(nome));

        if (existingUserByUsername.isPresent()) {
            return "Username already exists. Please choose a different username.";
        }

        if (existingByName.isPresent()) {
            return "Name already exists. Please choose a different name.";
        }

        if (nome == null || nome.isEmpty()) {
            return "Name cannot be null or empty. Please provide a valid name.";
        }

        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setNome(nome);
        newUser.setPassword(password);

        if (username.equals("admin") && password.equals("admin")) {
            newUser.setAdministrador(1);
        } else {
            newUser.setAdministrador(0);
        }

        usersRepository.save(newUser);
        return "User added successfully";
    }

    public boolean authenticateUser(String username, String password) {
        Users user = usersRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public String updateUser(String oldUsername, String nome, String username, String password) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepository.findByUsername(oldUsername));
        if (optionalUser.isEmpty()) {
            return "User not found";
        }
        Users user = optionalUser.get();

        Users existingByUsername = usersRepository.findByUsername(username);
        if (existingByUsername != null && !existingByUsername.getId().equals(user.getId())) {
            return "Username already exists. Please choose a different username.";
        }

        Users existingByName = usersRepository.findByNome(nome);
        if (existingByName != null && !existingByName.getId().equals(user.getId())) {
            return "Name already exists. Please choose a different name.";
        }

        user.setNome(nome);
        user.setUsername(username);
        user.setPassword(password);

        usersRepository.save(user);
        return "User updated successfully.";
    }

    public Users findUserByUsername(String username, String password) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        if (usersRepository.findByUsername(username).getId() != null) {
            return usersRepository.findByUsername(username);
        }
        System.out.println("User not found!");

        return null;
    }
}

