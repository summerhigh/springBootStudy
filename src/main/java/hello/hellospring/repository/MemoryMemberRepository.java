package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
import org.springframework.stereotype.Repository;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 해시맵으로 된 아이디와 멤버이름을 가져와서 아이디에 맞는
        // 멤버 객체를 넘긴다.
        // null일 경우를 처리하는 방법으로 Optional을 사용함
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
       // 람다가 뭔지 찾아봐야함. 멤버에서 이름을 가져올 떄 같은 이름이 있으면 가져온다.
       // 이름이 없으면 optional에 null이 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // member들을 반환한다.
    }

    public void clearStore(){
        store.clear();
    }
}
