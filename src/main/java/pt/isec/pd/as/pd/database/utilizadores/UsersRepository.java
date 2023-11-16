package pt.isec.pd.as.pd.database.utilizadores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>
{
    Users findByUsername(String username);
    //List<Users> findByUsername2(String username);
    Users findByNome(String nome);
}
