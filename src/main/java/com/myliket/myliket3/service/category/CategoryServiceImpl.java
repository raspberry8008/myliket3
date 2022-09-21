package com.myliket.myliket3.service.category;

import com.myliket.myliket3.domain.dto.request.category.CategoryDto;
import com.myliket.myliket3.domain.dto.response.category.CategoryDetailDto;
import com.myliket.myliket3.domain.dto.response.category.CategoryListDto;
import com.myliket.myliket3.domain.dto.response.common.Response;
import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Response allCategoryList() throws Exception {

        List<Category> resultList = categoryRepository.findAll();

        CategoryListDto categoryListDto = new CategoryListDto();

        return Response.builder()
                .resultList(resultList.stream()
                        .map(categoryListDto::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Response getCategoryDetail(CategoryDto.RequestInfo requestInfo) throws Exception {

        if (categoryRepository.findById(requestInfo.getCategoryId()).isPresent()) {
            return Response.builder().data(new CategoryDetailDto()).build();
        } else {
            Category resultDto = categoryRepository.getReferenceById(requestInfo.getCategoryId());
            return Response.builder().data(new CategoryDetailDto(resultDto)).build();
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
        categoryRepository.save(requestUpdate.toEntity());
    }

    @Transactional
    @Override
    public void deleteCategory(CategoryDto.RequestInfo requestInfo) throws Exception {
        categoryRepository.delete(requestInfo.toEntity());
    }
}
