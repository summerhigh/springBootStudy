package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

        // 각 테스트를 실행하기 전에 메모리를 새로 얻어와서 이를 Memberservice 객체를 생성함으로써
        // 같은 메모리를 사용하여 테스트를 할 수 있다..
        // 뭔말인지 알듯말듯 잘 모르겠는데..
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); // 메모리 클리어하기
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 예외처리 3차.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 예외처리 2차
        // assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // assertThrows : 예외 타입이 같은지 검사. (member2를 join하려고 했을 때 발생하는 예외와 IllegalState가)
        /* 예외처리 1차
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            // fail 메시지와 같은지, 즉 member2가 같은 Name으로 가입이 되지 않음을 확인함.
        }
        */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}