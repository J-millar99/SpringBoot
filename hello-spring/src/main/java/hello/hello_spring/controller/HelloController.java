package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // url 매칭
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // Model, View, Controller 방식
    // 뷰 리졸버에게 html의 렌더링을 부탁
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    // String Converter, Json Converter 를 사용
    @GetMapping("hello-string")
    @ResponseBody // ResponseBody를 사용하면 뷰 리졸버를 사용하지 않음
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
} 
