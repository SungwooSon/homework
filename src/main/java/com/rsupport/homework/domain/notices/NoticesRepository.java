package com.rsupport.homework.domain.notices;

import com.rsupport.homework.web.dto.NoticesListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticesRepository extends JpaRepository<Notices, Long> {
    @Query("SELECT n FROM Notices n ORDER BY n.id DESC")
    List<Notices> findAllDesc();

    //List<NoticesListResponseDto> findAllDescPaging(Pageable pageable);
}
