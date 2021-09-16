package com.rsupport.homework.service;

import com.rsupport.homework.domain.notices.Notices;
import com.rsupport.homework.domain.notices.NoticesRepository;
import com.rsupport.homework.web.dto.NoticesListResponseDto;
import com.rsupport.homework.web.dto.NoticesResponseDto;
import com.rsupport.homework.web.dto.NoticesSaveRequestDto;
import com.rsupport.homework.web.dto.NoticesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticesService {
    private final NoticesRepository noticesRepository;

    @Transactional
    public Long save(NoticesSaveRequestDto requestDto) {
        return noticesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, NoticesUpdateRequestDto requestDto) {
        Notices notices = noticesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        notices.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Notices notices = noticesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        noticesRepository.delete(notices);
    }

    @Transactional(readOnly = true)
    public NoticesResponseDto findById(Long id) {
        Notices entity = noticesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new NoticesResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<NoticesListResponseDto> findAllDesc() {

        return noticesRepository.findAllDesc().stream()
                .map(NoticesListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NoticesListResponseDto> findAllDescPaging(Pageable pageable) {
        return noticesRepository.findAll(pageable).stream()
                .map(NoticesListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Boolean getListCheck(Pageable pageable) {
        Page<Notices> all = noticesRepository.findAll(pageable);
        Boolean check = all.hasNext();
        return check;
    }
}
