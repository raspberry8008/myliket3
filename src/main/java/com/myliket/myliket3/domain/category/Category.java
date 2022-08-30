package com.myliket.myliket3.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(max=15)
    @Column(name = "categoryname")
    private String categoryName;

    @NotBlank
    @Size(min=2, max=2)
    @Column(name = "categorystate")
    private String categoryState;

    @NotNull
    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @NotNull
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
