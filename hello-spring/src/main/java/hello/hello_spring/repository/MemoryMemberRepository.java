package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 회원의 id, Member를 저장
    private static long sequence = 0L; // 회원의 id를 하나씩 배정

    @Override // 회원을 메모리에 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override // Id로 회원 조회
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override // 이름으로 회원 조회
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
        // 값(회원들)에서 값의 이름이 매개변수 name과 돌일한 어느것이든 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 모든 회원을 리스트로 반환
    }

    public void clearStore() {
        store.clear();
    }
}
