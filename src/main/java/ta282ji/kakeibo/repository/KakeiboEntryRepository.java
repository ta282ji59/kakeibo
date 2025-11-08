package ta282ji.kakeibo.repository;
import ta282ji.kakeibo.model.KakeiboEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KakeiboEntryRepository extends JpaRepository<KakeiboEntry, Long> {
    List<KakeiboEntry> findByDate(LocalDate date);
    List<KakeiboEntry> findByCategory(String category);
}
