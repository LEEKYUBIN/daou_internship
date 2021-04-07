package com.mobius.moa.controller;

import com.mobius.moa.dto.FiltersResponseDto;
import com.mobius.moa.service.FiltersService;
import com.mobius.moa.web.exception.CategoryNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "필터 API", description = "Filters API")
@RequiredArgsConstructor
@RestController
public class FiltersController {

    private final FiltersService filtersService;

    @ApiOperation(value = "필터 목록 조회")
    @GetMapping("/category/{category}/filter")
    public ResponseEntity<List<FiltersResponseDto>> findByName(@PathVariable("category") String category) {
        List<FiltersResponseDto> dto = filtersService.getFiltersList(category);
        if (dto.isEmpty()) {
            throw new CategoryNotFoundException(category);
        }
        return ResponseEntity.ok().body(dto);
    }
}
