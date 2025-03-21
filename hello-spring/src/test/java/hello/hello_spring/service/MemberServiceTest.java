package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberRepository.findById(saveId).get(); // 가입시킨 회원의 id로 조회한 회원이
        assertEquals(member.getName(), findMember.getName()); // 원래 회원가 동일한지 확인
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1); // 먼저 "spring" 회원 1을 가입시키고
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // "spring" 회원 2를 가입시키면 예외가 발생해야 한다.

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 에러메시지가 동일해야 한다.

        //then
    }

}