package com.mobius.moa.controller;

import com.mobius.moa.dto.CategoriesResponseDto;
import com.mobius.moa.dto.CategoriesSaveRequestDto;
import com.mobius.moa.service.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "상품군 API", description = "Categories API")
@RequiredArgsConstructor
@RestController
public class CategoriesController {

    private final CategoriesService categoriesService;

    @ApiOperation(value = "상품군 등록")
    @PostMapping("/categories")
    public void save(@RequestBody CategoriesSaveRequestDto requestDto) {
        categoriesService.save(requestDto);
    }


    @ApiOperation(value = "상품군 리스트 조회")
    @GetMapping("/categories")
    public List<CategoriesResponseDto> findAll() {
        return categoriesService.findAll();
    }
}
