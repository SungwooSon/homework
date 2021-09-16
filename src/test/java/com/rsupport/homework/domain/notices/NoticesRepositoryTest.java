package com.rsupport.homework.domain.notices;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NoticesRepositoryTest {

    @Autowired
    NoticesRepository noticesReporitory;

    @AfterEach
    public void cleanup() {
        noticesReporitory.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        noticesReporitory.save(Notices.builder()
                .title(title)
                .content(content)
                .author("ssw0418@kakao.com")
                .build());

        //when
        List<Notices> noticesList = noticesReporitory.findAll();

        //then
        Notices notices = noticesList.get(0);
        assertThat(notices.getTitle()).isEqualTo(title);
        assertThat(notices.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2021, 9, 16, 11, 4, 0);
        noticesReporitory.save(Notices.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Notices> NoticesList = noticesReporitory.findAll();

        //then
        Notices notices = NoticesList.get(0);

        //System.out.println(">>>>>>>>> createDate=" + Notices.getCreatedDate() + ", modifiedDate=" + Notices.getModifiedDate());

        assertThat(notices.getCreatedDate()).isAfter(now);
        assertThat(notices.getModifiedDate()).isAfter(now);
    }
}