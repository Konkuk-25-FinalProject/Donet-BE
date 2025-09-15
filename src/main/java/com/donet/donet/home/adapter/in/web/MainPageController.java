package com.donet.donet.home.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainPageController {
    @Operation(summary = "메인페이지", description = "메인 페이지 조회 API입니다.")
    @GetMapping("/main")
    public ResponseMainPageDTO mainPage(){
        return null;
    }


}
