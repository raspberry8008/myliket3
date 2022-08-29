package com.myliket.myliket3.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 *  Category : 카테고리 상세정보
 *
 *  UUID categoryId : 카테고리 아이디/UUID
 *  String categoryName : 카테고리 이름
 *  String categoryState : 카테고리 상태 코드
 *  LocalDateTime categoryCreatedAt : 카테고리 최초 등록일시
 *  LocalDateTime categoryUpdatedAt : 카테고리 마지막 수정일시
 */

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name ="category")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "categoryid", columnDefinition = "BINARY(16)")
    private UUID categoryId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "categoryid")
//    private Long categoryId;


    @Column(name = "categoryname")
    private String categoryName;

    @Column(name = "categorystate")
    private String categoryState;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;


    @Builder
    public Category(UUID categoryId, String categoryName, String categoryState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryState = categoryState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE, orphanRemoval = true)
//    private List<Category> categories = new ArrayList<>();

}
