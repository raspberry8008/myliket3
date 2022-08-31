package com.myliket.myliket3.service.category;

import com.myliket.myliket3.domain.category.Category;
import com.myliket.myliket3.domain.category.CategoryRepository;
import com.myliket.myliket3.web.dto.CategoryDto;
import com.myliket.myliket3.web.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Response allCategoryList() throws Exception {

        return Response.builder().resultList(categoryRepository.findAll()).build();
    }

    @Override
    public Response getCategoryDetail(CategoryDto.RequestInfo requestInfo) throws Exception {

        Optional<Category> result = categoryRepository.findById(requestInfo.getCategoryId());

        if (ObjectUtils.isEmpty(result) || ObjectUtils.nullSafeEquals(null, result)) {
            Category categoryVO2 = Category.builder().build();
            return Response.builder().data(categoryVO2).build();
        } else {
            return Response.builder().data(result).build();
        }

    }

    @Transactional
    @Override
    public void insertCategory(CategoryDto.RequestInsert requestInsert) throws Exception {
        categoryRepository.save(requestInsert.toEntity());
    }

    @Transactional
    @Override
    public void updateCategory(CategoryDto.RequestUpdate requestUpdate) throws Exception {
        Optional<Category> category = categoryRepository.findById(requestUpdate.getCategoryId());
        requestUpdate.setCategoryId(category.get().getCategoryId());
        categoryRepository.save(requestUpdate.toEntity());
    }

    @Transactional
    @Override
    public void deleteCategory(CategoryDto.RequestInfo requestInfo) throws Exception {
        Category category = categoryRepository.findById(requestInfo.getCategoryId()).get();
        categoryRepository.delete(category);
    }
}
