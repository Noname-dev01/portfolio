package com.example.portfolio.web;

import com.example.portfolio.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/character")
    public String character() {return "character";}

    @GetMapping("/noticeBoard")
    public String noticeBoard(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "noticeBoard";
    }

    @GetMapping("/gameIntroduce")
    public String gameIntroduce() { return "gameIntroduce";}

    @GetMapping("/posts/save")
    public String postsSave() {return "posts-save";}

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(){
        return "posts-update";
    }

    @GetMapping("/serviceTest")
    public String serviceTest(){return "service-test";}
}
