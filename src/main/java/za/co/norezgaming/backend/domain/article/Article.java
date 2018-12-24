package za.co.norezgaming.backend.domain.article;

import lombok.*;
import za.co.norezgaming.backend.domain.Tag;
import za.co.norezgaming.backend.domain.account.Account;
import za.co.norezgaming.backend.domain.content.ArticleContent;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String text;

//    @ManyToMany
//    private List<ArticleContent> images;
//
//    @ManyToMany
//    private List<Tag> tags;

    @ManyToOne
    private Account account;
}
