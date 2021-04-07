package com.mobius.moa.service;

import com.mobius.moa.domain.entity.Categories;
import com.mobius.moa.domain.entity.Information;
import com.mobius.moa.domain.entity.Items;
import com.mobius.moa.domain.repository.CategoriesRepository;
import com.mobius.moa.domain.repository.InformationRepository;
import com.mobius.moa.domain.repository.ItemsRepository;
import com.mobius.moa.dto.ItemResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsServiceTest {
    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    ItemsService itemsService;

    private Categories saveCategories(String name, String text, String img) {
        return categoriesRepository.save(Categories.builder()
                .name(name)
                .text(text)
                .img(img).build());
    }

    private void saveItems(Categories category, String itemName, int n) {
        for (int i = 1; i <= n; i++) {
            itemsRepository.save(Items.builder()
                    .categories(category)
                    .name(itemName + i)
                    .price(100)
                    .reviewNum(100)
                    .build());
        }
    }

    private Items saveItemOne(Categories categories, String itemName) {
        return itemsRepository.save(Items.builder()
                .name(itemName)
                .price(100)
                .reviewNum(100)
                .categories(categories).build());
    }

    private void saveInformation(String attribute, Items item) {
        Information info = Information.builder()
                .attributeText(attribute).build();
        info.setItems(item);
        informationRepository.save(info);
    }

    @Transactional
    @Test
    public void 상품군별_전체조회() {
        // given
        Categories laptopCategory = saveCategories("노트북", "laptop", "laptop.img");
        Categories smartphoneCategory = saveCategories("스마트폰", "smartphone", "smartphone.img");

        String laptopItem = "laptop";
        saveItems(laptopCategory, laptopItem, 10);

        String smartphoneItem = "smartphone";
        saveItems(smartphoneCategory, smartphoneItem, 4);

        // when
        List<ItemResponseDto> laptopDtoList = itemsService.searchAllItems(laptopCategory.getText(), 0, 6);
        List<ItemResponseDto> smartphoneDtoList = itemsService.searchAllItems(smartphoneCategory.getText(), 0, 6);

        // then
        assertThat(laptopDtoList.size()).isEqualTo(6);
        assertThat(smartphoneDtoList.size()).isEqualTo(4);

        laptopDtoList.forEach(laptopDto -> assertThat(laptopDto.getName().contains(laptopItem)).isTrue());
        smartphoneDtoList.forEach(smartphoneDto -> assertThat(smartphoneDto.getName().contains(smartphoneItem)).isTrue());
    }


    @Transactional
    @Test
    public void 상품_조건별_조회() {
        // given
        Categories laptopCategory = saveCategories("노트북", "laptop", "laptop.img");

        String laptopItem1 = "laptop1";
        Items item1 = saveItemOne(laptopCategory, laptopItem1);
        saveInformation("i3", item1);

        String laptopItem2 = "laptop2";
        Items item2 = saveItemOne(laptopCategory, laptopItem2);
        saveInformation("i3", item2);

        String laptopItem3 = "laptop3";
        Items item3 = saveItemOne(laptopCategory, laptopItem3);
        saveInformation("i5", item3);

        // when
        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("cpu", "i5");
        List<ItemResponseDto> itemsDtoList = itemsService.searchItemsByAttribute(laptopCategory.getText(), attributesMap, 0, 6);

        // then
        assertThat(itemsDtoList.size()).isEqualTo(1);
        assertThat(itemsDtoList.get(0).getName()).isEqualTo(laptopItem3);
    }
}
