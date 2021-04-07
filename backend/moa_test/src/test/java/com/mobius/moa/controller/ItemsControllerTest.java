package com.mobius.moa.controller;

import com.mobius.moa.domain.Items;
import com.mobius.moa.repository.ItemsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ItemsRepository itemsRepository;

    @Test
    public void Items_등록된다() throws Exception {
        //given
        String name = "삼성노트북";

        int categoryNo = 1;

        int price = 200000;

        int review = 80;

        int reviewNum = 123;

        LocalDate regdate = LocalDate.ofEpochDay(2021-03-01);

        String img = "이미지 url";

        ItemsSaveRequestDto requestDto = ItemsSaveRequestDto.builder()
                .name(name)
                .price(price)
                .review(review)
                .reviewNum(reviewNum)
                .regdate(regdate)
                .img(img)
                .build();

        String url = "http://localhost:" + port + "/items";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        //then
        List<Items> all = itemsRepository.find();
        assertThat(all.get(all.size() - 1).getImg()).isEqualTo(img);
        assertThat(all.get(all.size() - 1).getName()).isEqualTo(name);
    }
}
