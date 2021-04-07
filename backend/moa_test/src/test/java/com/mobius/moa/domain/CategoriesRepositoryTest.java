package com.mobius.moa.domain;

import com.mobius.moa.repository.CategoriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriesRepositoryTest {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Transactional
    @Test
    public void 카테고리저장_불러오기() {
        // given
        String img = "테스트 이미지";
        String name = "테스트 상품군";
        String text = "테스트 상품군 텍스트";

        categoriesRepository.save(Categories.builder().img(img).name(name).text(text).build());
        // when
        List<Categories> categoriesList = categoriesRepository.findAll();

        // then
        Categories categories = categoriesList.get(categoriesList.size()-1);
        assertThat(categories.getImg()).isEqualTo(img);
        assertThat(categories.getName()).isEqualTo(name);
        assertThat(categories.getText()).isEqualTo(text);
    }

    @Test
    public void 카테고리_이름으로_조회(){
        String text = "laptop";
        Categories categories = categoriesRepository.findByName(text);
        assertThat(categories.getText()).isEqualTo(text);
    }
}
