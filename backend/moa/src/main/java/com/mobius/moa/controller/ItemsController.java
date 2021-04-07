package com.mobius.moa.controller;

import com.mobius.moa.dto.ItemDetailDto;
import com.mobius.moa.dto.ItemResponseDto;
import com.mobius.moa.service.ItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.mobius.moa.common.Constants.LOAD_ITEM_COUNT;

@Api(tags = "상품 API", description = "Items API")
@RequiredArgsConstructor
@RestController
public class ItemsController {
    private final ItemsService itemsService;

    @ApiOperation(value = "상품 목록 조회")
    @GetMapping("/category/{category}/items/page/{page}")
    public ResponseEntity<List<ItemResponseDto>> searchItems(@PathVariable("category") String category,
                                                             @PathVariable("page") int page,
                                                             @RequestParam Map<String, String> attributesMap) {
        int loadCount = LOAD_ITEM_COUNT.getNumber();
        List<ItemResponseDto> dto;
        if (attributesMap.isEmpty()) {
            dto = itemsService.searchAllItems(category, (page - 1) * loadCount, loadCount);
        } else {
            dto = itemsService.searchItemsByAttribute(category, attributesMap, (page - 1) * loadCount, loadCount);
        }
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "전체 상품 수 조회")
    @GetMapping("/category/{category}/items/count")
    private ResponseEntity<Long> getItemsCount(@PathVariable("category") String category,
                                               @RequestParam Map<String, String> attributesMap) {
        long total;
        if (attributesMap.isEmpty()) {
            total = itemsService.searchItemsCount(category);
        } else {
            total = itemsService.searchItemsByAttributeCount(category, attributesMap);
        }
        return ResponseEntity.ok().body(total);
    }

    @ApiOperation(value = "상품 상세정보 조회")
    @GetMapping("/information")
    public ResponseEntity<List<List<ItemDetailDto>>> findByItemsNo(@RequestParam("items") List<Integer> items) {
        return ResponseEntity.ok().body(itemsService.getItemInformation(items));
    }
}
