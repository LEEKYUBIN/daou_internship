package com.mobius.moa.controller;

import com.mobius.moa.dto.ItemsResponseDto;
import com.mobius.moa.service.ItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "상품 API", description = "Items API")
@RequiredArgsConstructor
@RestController
public class ItemsController {
    private final ItemsService itemsService;

    @ApiOperation(value = "상품 리스트 조회")
    @GetMapping("/find/{i}")
    public List<ItemsResponseDto> find(@PathVariable("i")int i) {
        return itemsService.find(i);
    }


    @GetMapping("/items/{category}/{attributeText}/{pageIdx}")
    public List<ItemsResponseDto> getItemsListByText(@PathVariable("category") String category, @PathVariable("attributeText") String attributeText, @PathVariable("pageIdx") int pageIdx) {
        return itemsService.getItemsListByText(category, attributeText, pageIdx);
    }
}
