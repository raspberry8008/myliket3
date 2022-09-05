package com.myliket.myliket3.domain.entity.category;

import com.myliket.myliket3.domain.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/*
 *  Category : 카테고리 상세정보
 *
 *  UUID categoryId : 카테고리 아이디
 *  String categoryName : 카테고리 이름
 *  CategoryState categoryState : 카테고리 상태
 */

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "categoryid", columnDefinition = "BINARY(16)")
    private UUID categoryId;
    @NotNull
    @Size(max = 15)
    @Column(name = "categoryname")
    private String categoryName;


    @Builder
    public Category(CategoryState categoryState, UUID categoryId, String categoryName) {
        this.categoryState = categoryState;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    @ManyToOne
    @JoinColumn(name = "categorystatecode")
    private CategoryState categoryState;

}
