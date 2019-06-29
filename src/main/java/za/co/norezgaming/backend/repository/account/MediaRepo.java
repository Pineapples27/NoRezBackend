package za.co.norezgaming.backend.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.norezgaming.backend.domain.account.Media;

@Repository
public interface MediaRepo extends JpaRepository<Media, Long> {
}
