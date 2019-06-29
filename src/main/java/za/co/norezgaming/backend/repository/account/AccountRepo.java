package za.co.norezgaming.backend.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.norezgaming.backend.domain.account.Account;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByGamerTag(String gamerTag);
    Optional<Account> findByGamerTagIgnoreCase(String gamerTag);
    Optional<Account> findByAccountUUID(UUID accountUUID);
}
