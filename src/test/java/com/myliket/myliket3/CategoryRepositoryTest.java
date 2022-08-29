package com.myliket.myliket3;

import com.myliket.myliket3.domain.Category;
import com.myliket.myliket3.domain.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("카테고리 등록 테스트")
    @Test
    public void categorySaveTest() {

        Category category = Category.builder()
                .categoryId(UUID.randomUUID())
                .categoryName("Test..")
                .categoryState("CY")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        categoryRepository.save(category);
    }

    @DisplayName("카테고리 전체 목록 조회 테스트")
    @Test
    public void categoryFindAllTest() {

        List<Category> categories = categoryRepository.findAll();

    }

    @DisplayName("카테고리 단일 조회 테스트")
    @Test
    public void categoryFindIdTest() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());


        Optional<Category> category = categoryRepository.findById(testUUID);

        category.ifPresent(selectCategory-> {
            System.out.println("category : " + selectCategory);
            System.out.println("category : " + selectCategory.getCategoryId());
        });

        Assert.assertTrue(category.isPresent());

    }

    @DisplayName("카테고리 수정 테스트")
    @Test
    public void categoryUpdateTest() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        Optional<Category> category = categoryRepository.findById(testUUID);
        Assert.assertTrue(category.isPresent());

        Category categoryInfo = Category.builder()
                .categoryId(testUUID)
                .categoryName("수정 Test..")
                .categoryState("CY")
                .createdAt(category.get().getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        categoryRepository.save(categoryInfo);
    }

    @DisplayName("카테고리 삭제 테스트")
    @Test
    public void categoryDeleteTest() {

        String uuid = "d80d69ac-cd1d-4f2e-a103-29384798800c".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        // 1. 카테고리 조회
        Category category = categoryRepository.findById(testUUID).get();

        // 2. 카테고리 삭제
        categoryRepository.delete(category);

    }
}
