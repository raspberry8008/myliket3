package com.myliket.myliket3.service.category;

import com.myliket.myliket3.domain.category.Category;
import com.myliket.myliket3.domain.category.CategoryRepository;
import com.myliket.myliket3.web.dto.CategoryDto;
import com.myliket.myliket3.web.dto.CategoryResponseDto;
import com.myliket.myliket3.web.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Optional<Category> resultEntity = categoryRepository.findById(requestInfo.getCategoryId());

        if (resultEntity.isEmpty()){
            return Response.builder().data(new CategoryResponseDto(new Category())).build();
        } else {
            Category resultDto =categoryRepository.getReferenceById(requestInfo.getCategoryId());
            return Response.builder().data(new CategoryResponseDto(resultDto)).build();
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
