package com.rsupport.homework.web;


import com.rsupport.homework.domain.notices.Notices;
import com.rsupport.homework.domain.notices.NoticesRepository;
import com.rsupport.homework.web.dto.NoticesSaveRequestDto;
import com.rsupport.homework.web.dto.NoticesUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticesApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NoticesRepository noticesRepository;

    @AfterEach
    public void tearDown() throws Exception {
        noticesRepository.deleteAll();
    }

    @Test
    public void Notices_등록된다() throws Exception {
        //given
        String title = "title";
        String content = "content";
        NoticesSaveRequestDto requestDto = NoticesSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/notices";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notices> all = noticesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Notices_수정된다() throws Exception {
        //given
        Notices savedNotices = noticesRepository.save(Notices.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedNotices.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        NoticesUpdateRequestDto requestDto = NoticesUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/notices/" + updateId;

        HttpEntity<NoticesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notices> all = noticesRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}