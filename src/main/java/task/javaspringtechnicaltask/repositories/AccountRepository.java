package task.javaspringtechnicaltask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.javaspringtechnicaltask.models.dao.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByNameAndSurname(String name, String surname);

    @Query("select acc from Account acc where acc.name=:name and acc.surname=:surname")
    Account findByNameAndSurname(String name, String surname);

    default Account findAccountByNameAndSurname(String name, String surname) throws Exception {
        Account account = findByNameAndSurname(name, surname);
        if (account == null)
            throw new Exception("Account does not exist");
        return account;
    }

    default Boolean existsAccountByNameAndSurname(String name, String surname) throws Exception {
        if (!existsByNameAndSurname(name, surname))
            return true;
        else throw new Exception("Account with name= " + name +
                " and surname= " + surname + " already exists");
    }
    
}
