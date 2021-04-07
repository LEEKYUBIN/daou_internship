package com.mobius.moa.controller;

import com.mobius.moa.dto.CategoriesResponseDto;
import com.mobius.moa.service.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "상품군 API", description = "Categories API")
@RequiredArgsConstructor
@RestController
public class CategoriesController {

    private final CategoriesService categoriesService;

    @ApiOperation(value = "상품군 목록 조회")
    @GetMapping("/categories")
    public ResponseEntity<List<CategoriesResponseDto>> findAll() {
        return ResponseEntity.ok().body(categoriesService.findAll());
    }
}
