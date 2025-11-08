package ta282ji.kakeibo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate; // 日付を扱うためのクラス

@Entity
@Table(name = "kakeibo_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakeiboEntry {

    @Id // 主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date; // 日付

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double amount;

    private String description;
}
