package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content; //섬머노트 라이버리 <html>태그가 섞여서 디자인됨.

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Many, User = One
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 하나의 게시글은 여러개의 답변을 갖는다. mappedBy 연관관계의 주인이 아니다(나는 pk가 아니에요) DB에 컬럼을 만들지 마세요.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
