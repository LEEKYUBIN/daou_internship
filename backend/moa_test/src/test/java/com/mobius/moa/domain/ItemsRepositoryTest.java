package com.mobius.moa.domain;

import com.mobius.moa.repository.CategoriesRepository;
import com.mobius.moa.repository.ItemsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsRepositoryTest {
    @Autowired
    ItemsRepository itemsRepository;

    @Transactional
    @Test
    public void 아이템들_불러오기() {
        // given
        String name = "삼성노트북";


        int price = 200000;

        int review = 80;

        int reviewNum = 123;

        LocalDate regdate = LocalDate.ofEpochDay(2021-03-01);

        String img = "이미지 url";

        itemsRepository.save(Items.builder().name(name)

                .price(price)
                .review(review)
                .reviewNum(reviewNum)
                .regdate(regdate)
                .img(img)
                .build());
        // when
        List<Items> itemsList = itemsRepository.find();

        // then
        Items items = itemsList.get(itemsList.size()-1);
        assertThat(items.getImg()).isEqualTo(img);

        assertThat(items.getName()).isEqualTo(name);
        assertThat(items.getPrice()).isEqualTo(price);
        assertThat(items.getRegdate()).isEqualTo(regdate);
        assertThat(items.getReview()).isEqualTo(review);
        assertThat(items.getReviewNum()).isEqualTo(reviewNum);

    }

//    @Test
//    public void 카테고리_이름으로_조회(){
//        String text = "laptop";
//        Categories categories = categoriesRepository.findByName(text);
//        assertThat(categories.getText()).isEqualTo(text);
//    }
}
