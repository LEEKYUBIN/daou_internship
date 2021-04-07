package com.mobius.moa.controller;

import com.mobius.moa.domain.Categories;
import com.mobius.moa.dto.CategoriesSaveRequestDto;
import com.mobius.moa.repository.CategoriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Test
    public void Categories_등록된다() throws Exception {
        //given
        String img = "img";
        String name = "에어컨";
        String text = "air conditioner";
        CategoriesSaveRequestDto requestDto = CategoriesSaveRequestDto.builder()
                .img(img)
                .name(name)
                .text(text)
                .build();

        String url = "http://localhost:" + port + "/categories";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        //then
        List<Categories> all = categoriesRepository.findAll();
        assertThat(all.get(all.size() - 1).getImg()).isEqualTo(img);
        assertThat(all.get(all.size() - 1).getName()).isEqualTo(name);
    }
}