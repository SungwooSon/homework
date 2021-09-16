package com.rsupport.homework.web;

import com.rsupport.homework.service.NoticesService;
import com.rsupport.homework.web.dto.NoticesListResponseDto;
import com.rsupport.homework.web.dto.NoticesResponseDto;
import com.rsupport.homework.web.dto.NoticesSaveRequestDto;
import com.rsupport.homework.web.dto.NoticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticesApiController {
    private final NoticesService noticesService;

    @PostMapping("/api/v1/notices")
    public Long save(@RequestBody NoticesSaveRequestDto requestDto) {
        return noticesService.save(requestDto);
    }

    @PutMapping("/api/v1/notices/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticesUpdateRequestDto requestDto) {
        return noticesService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/notices/{id}")
    public Long delete(@PathVariable Long id) {
        noticesService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/notices/{id}")
    public NoticesResponseDto findById(@PathVariable Long id) {
        return noticesService.findById(id);
    }

    @GetMapping("/api/v1/notices/list")
    public List<NoticesListResponseDto> findAll() {
        return noticesService.findAllDesc();
    }

}
