package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // GET메소드가 해당 url에서 요청
    public String home() {
        return "home"; // home.html 반환
    }
}
