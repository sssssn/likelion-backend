package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home") // 최상단에 있을 경우 중복 코드를 줄일 수 있다. (스프링 특정 버전 이상만 가능)
public class HomeController {
//    private int count = 0; // 전역 변수
    private final CountService countService;
    public HomeController(CountService countService) {
        this.countService = countService;
    }

//    @RequestMapping("/home")
    @RequestMapping("") // 중복 코드를 줄일 수 있는 예)
    public String index(Model model) {
//        int count = 0; 지역 변수라서 카운팅 X
//        count++;
        int count = countService.addCount();
        System.out.println(count);
        model.addAttribute("count", count);
        return "home";
    }

    @RequestMapping("/reset")
    public String reset() {
//        count = 0;
        countService.reset();
        System.out.println("reset");
        return "redirect:/home";
    }
}
