package ta282ji.kakeibo.controller;

import ta282ji.kakeibo.model.KakeiboEntry;
import ta282ji.kakeibo.service.KakeiboEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/kakeibo")
public class KakeiboEntryController {

    private final KakeiboEntryService kakeiboEntryService;

    @Autowired
    public KakeiboEntryController(KakeiboEntryService kakeiboEntryService) {
        this.kakeiboEntryService = kakeiboEntryService;
    }

    // GET: 全ての家計簿エントリを取得
    // 例: GET http://localhost:8080/api/kakeibo
    @GetMapping
    public ResponseEntity<List<KakeiboEntry>> getAllEntries() {
        List<KakeiboEntry> entries = kakeiboEntryService.getAllEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    // GET: IDで家計簿エントリを取得
    // 例: GET http://localhost:8080/api/kakeibo/1
    @GetMapping("/{id}")
    public ResponseEntity<KakeiboEntry> getEntryById(@PathVariable Long id) {
        return kakeiboEntryService.getEntryById(id)
                .map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST: 新しい家計簿エントリを作成
    // 例: POST http://localhost:8080/api/kakeibo
    // Body: {"date": "2023-11-08", "category": "食費", "amount": 1200.0, "description": "ランチ代"}
    @PostMapping
    public ResponseEntity<KakeiboEntry> createEntry(@RequestBody KakeiboEntry entry) {
        KakeiboEntry createdEntry = kakeiboEntryService.createEntry(entry);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    // PUT: 既存の家計簿エントリを更新
    // 例: PUT http://localhost:8080/api/kakeibo/1
    // Body: {"date": "2023-11-08", "category": "食費", "amount": 1500.0, "description": "少し豪華なランチ"}
    @PutMapping("/{id}")
    public ResponseEntity<KakeiboEntry> updateEntry(@PathVariable Long id, @RequestBody KakeiboEntry entryDetails) {
        return kakeiboEntryService.updateEntry(id, entryDetails)
                .map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: IDで家計簿エントリを削除
    // 例: DELETE http://localhost:8080/api/kakeibo/1
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEntry(@PathVariable Long id) {
        if (kakeiboEntryService.deleteEntry(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 削除成功 (コンテンツなし)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // エントリが見つからなかった場合
        }
    }

    // GET: 特定の日付の家計簿エントリを取得
    // 例: GET http://localhost:8080/api/kakeibo/by-date?date=2023-11-08
    @GetMapping("/by-date")
    public ResponseEntity<List<KakeiboEntry>> getEntriesByDate(@RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate date) {
        List<KakeiboEntry> entries = kakeiboEntryService.getEntriesByDate(date);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    // GET: 特定のカテゴリの家計簿エントリを取得
    // 例: GET http://localhost:8080/api/kakeibo/by-category?category=食費
    @GetMapping("/by-category")
    public ResponseEntity<List<KakeiboEntry>> getEntriesByCategory(@RequestParam String category) {
        List<KakeiboEntry> entries = kakeiboEntryService.getEntriesByCategory(category);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
