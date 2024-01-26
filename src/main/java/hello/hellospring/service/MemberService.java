package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
public class MemberService {

  // private  final MemberRepository memberRepository = new MemoryMemberRepository();
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /* 회원 가입 */
  public Long join(Member member) {
    // 같은 이름이 있는 중복 회원 X

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m-> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
    // Optional을 쓰면 이와같은 처리가 가능함.
    // result에 어떤 값이라도 존재하면 익셉션이 뜬다.
    // 아래와 같이 쓰는 것을 더 권장함
    validateDuplicateMember(member);

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  /* 전체 회원 조회*/
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
