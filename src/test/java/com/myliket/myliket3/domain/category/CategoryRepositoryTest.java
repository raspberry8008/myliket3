package com.myliket.myliket3.domain.category;

import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.category.CategoryRepository;
import com.myliket.myliket3.domain.entity.category.CategoryRepositorySupport;
import com.myliket.myliket3.domain.entity.category.CategoryState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryRepositorySupport categoryRepositorySupport;


    @Test
    public void 카테고리_등록하기() {

        Category category = Category.builder()
                .categoryId(UUID.randomUUID())
                .categoryName("Test")
                .categoryState(CategoryState.builder().categoryStateCode("CY").build())
                .build();

        categoryRepository.save(category);
    }

    @Test
    public void 카테고리_전체목록조회() {

        List<Category> categories = categoryRepository.findAll();

    }

    @Test
    public void 카테고리_상세조회() {

        String uuid = "979eff61-f71d-458a-9efe-6c26a9834749".replace("-", "");
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
                .categoryState(CategoryState.builder().categoryStateCode("CY").build())
                .build();

        categoryRepository.save(categoryInfo);
    }

    @Test
    public void 카테고리_삭제하기() {

        String uuid = "ff12b9e4-9866-4a61-8778-8343ff01e161".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        // 1. 카테고리 조회
        Category category = categoryRepository.findById(testUUID).get();

        // 2. 카테고리 삭제
        categoryRepository.delete(category);

    }

//    @Test
//    public void 카테고리_이름검색하기() {
//
//        String categoryName = "Test..";
//
//        List<Category> categories = categoryRepositorySupport.findByName(categoryName);
//
//    }
}
