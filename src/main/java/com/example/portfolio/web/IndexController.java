package com.example.portfolio.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/character")
    public String character() {return "character";}

    @GetMapping("/gameIntroduce")
    public String gameIntroduce() { return "gameIntroduce";}

    @GetMapping("/serviceTest")
    public String serviceTest(){return "service-test";}
}
