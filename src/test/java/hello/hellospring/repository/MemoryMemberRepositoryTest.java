package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    // MemoryMemberRepository가 정상작동하는지 테스트해보기위해 객체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        // AfterEach : 한 동작마다 이 메소드를 실행하도록 하는 어노테이션
        // 한 테스트 케이스를 실행할 때마다 클리어해줘야함
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // System.out.println("result = " + (result == member)); // 기본방식.
        // Assertions.assertEquals(member, result); // 초록체크 생김
        assertThat(member).isEqualTo(result);   // alt + insert 해서 static import했음. 초록체크 생김

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

       List<Member> result =  repository.findAll();

       assertThat(result.size()).isEqualTo(2);
    }

    // 테스트 파일 전체를 run하면 오류가 생긴다.
    // 각 테스트의 순서가 랜덤이기 때문에 이미 생성한 객체가 중복되기 때문
    // 그래서 한 테스트를 하고 클리어해줘야 함

}
