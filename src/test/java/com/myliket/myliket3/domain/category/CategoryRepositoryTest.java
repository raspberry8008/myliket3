package com.myliket.myliket3.domain.category;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@EnableJpaAuditing
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("카테고리 등록 테스트")
    @Test
    public void 카테고리_등록하기() {

        Category category = Category.builder()
                .categoryId(UUID.randomUUID())
                .categoryName("Test")
                .categoryState("CY")
                .build();

        categoryRepository.save(category);
    }

    @Test
    public void 카테고리_전체목록조회() {

        List<Category> categories = categoryRepository.findAll(Sort.by("createdat").ascending());

    }

    @Test
    public void 카테고리_상세조회() {

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

    @Test
    public void 카테고리_수정하기() {

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
                .build();

        categoryRepository.save(categoryInfo);
    }

    @Test
    public void 카테고리_삭제하기() {

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
