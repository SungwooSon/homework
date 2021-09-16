package com.rsupport.homework.web;

import com.rsupport.homework.service.NoticesService;
import com.rsupport.homework.web.dto.NoticesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final NoticesService noticesService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("notices", noticesService.findAllDescPaging(pageable));
        model.addAttribute("check", noticesService.getListCheck(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        return "index";
    }

    @GetMapping("/notices/save")
    public String noticesSave() {
        return "notices-save";
    }

    @GetMapping("/notices/update/{id}")
    public String noticesUpdate(@PathVariable Long id, Model model) {
        NoticesResponseDto dto = noticesService.findById(id);
        model.addAttribute("notice", dto);

        return "notices-update";
    }
}
