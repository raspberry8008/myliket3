package com.myliket.myliket3.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="category")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "categoryId", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "categoryState")
    private String categoryState;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE, orphanRemoval = true)
//    private List<Category> categories = new ArrayList<>();

}
