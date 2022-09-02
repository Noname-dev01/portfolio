package com.example.portfolio.web;

import com.example.portfolio.domain.posts.Posts;
import com.example.portfolio.repository.PostSearchCond;
import com.example.portfolio.repository.PostUpdateDto;
import com.example.portfolio.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/posts")
public class PostsController {

    private final PostService postService;

    @GetMapping
    public String noticeBoard(@ModelAttribute("postSearch")PostSearchCond postSearch, Model model){
        List<Posts> posts = postService.findPosts(postSearch);
        model.addAttribute("posts", posts);
        return "posts/noticeBoard";
    }
    @GetMapping("/{postId}")
    public String posts(@PathVariable long postId, Model model) {
        Posts post = postService.findById(postId).get();
        model.addAttribute("post", post);
        return "posts/saveResult";
    }

    @GetMapping("/add")
    public String addForm() {
        return "posts/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Posts posts, RedirectAttributes redirectAttributes) {
        Posts savedPosts = postService.save(posts);
        redirectAttributes.addAttribute("postId", savedPosts.getId());
        redirectAttributes.addAttribute("status", true);
        log.info("posts={}",posts);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Posts post = postService.findById(postId).get();
        model.addAttribute("post", post);
        return "posts/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @ModelAttribute PostUpdateDto updateParam) {
        postService.update(postId, updateParam);
        return "redirect:/posts/{postId}";
    }
}
