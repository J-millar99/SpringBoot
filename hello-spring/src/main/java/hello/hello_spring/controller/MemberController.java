package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컴포넌트가 스캔함
public class MemberController {
    private final MemberService memberService;

    @Autowired // 자동으로 의존성이 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // GET메소드가 해당 url로 요청
    public String createForm() {
        return "members/createMemberForm"; // 해당 html을 반환
    }

    @PostMapping("/members/new") // POST메소드가 해당 url로 요청
    public String create(MemberForm form) { // 멤버 폼 객체를 전달받음
        Member member = new Member(); // 멤버 객체 생성
        member.setName(form.getName()); // 이름 지정
        memberService.join(member); // 가입 메소드 호출
        return "redirect:/"; // 홈 화면으로 이동
    }

    @GetMapping("/members") // GET메소드가 해당 url로 요청
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // memberService의 list반환 findMembers를 호출
        model.addAttribute("members", members); // 모델 객체에 속성을 추가
        return "members/memberList"; // thymeleaf에서 쓸 수 있도록 html반환
    }
}
