package ta282ji.kakeibo.service;

import ta282ji.kakeibo.model.KakeiboEntry;
import ta282ji.kakeibo.repository.KakeiboEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class KakeiboEntryService {

    private final KakeiboEntryRepository kakeiboEntryRepository;

    @Autowired
    public KakeiboEntryService(KakeiboEntryRepository kakeiboEntryRepository) {
        this.kakeiboEntryRepository = kakeiboEntryRepository;
    }

    // 全ての家計簿エントリを取得
    public List<KakeiboEntry> getAllEntries() {
        return kakeiboEntryRepository.findAll();
    }

    // IDで家計簿エントリを取得
    public Optional<KakeiboEntry> getEntryById(Long id) {
        return kakeiboEntryRepository.findById(id);
    }

    // 新しい家計簿エントリを作成
    public KakeiboEntry createEntry(KakeiboEntry entry) {
        return kakeiboEntryRepository.save(entry);
    }

    // 家計簿エントリを更新
    public Optional<KakeiboEntry> updateEntry(Long id, KakeiboEntry entryDetails) {
        return kakeiboEntryRepository.findById(id).map(entry -> {
            entry.setDate(entryDetails.getDate());
            entry.setCategory(entryDetails.getCategory());
            entry.setAmount(entryDetails.getAmount());
            entry.setDescription(entryDetails.getDescription());
            return kakeiboEntryRepository.save(entry);
        });
    }

    // 家計簿エントリを削除
    public boolean deleteEntry(Long id) {
        if (kakeiboEntryRepository.existsById(id)) {
            kakeiboEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 特定の日付の家計簿エントリを取得
    public List<KakeiboEntry> getEntriesByDate(LocalDate date) {
        return kakeiboEntryRepository.findByDate(date);
    }

    // 特定のカテゴリの家計簿エントリを取得
    public List<KakeiboEntry> getEntriesByCategory(String category) {
        return kakeiboEntryRepository.findByCategory(category);
    }
}
