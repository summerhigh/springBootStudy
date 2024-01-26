package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

  // 이 인터페이스를 보고 SpringDataJPA가 알아서 구현체를 만들어줌
  // 그 구현체를 나는 그냥 가져다 쓰면 된다. (SpringConfig.java)
  // JPQL select m from Member m where m.name = ?
  @Override
  Optional<Member> findByName(String name);
}
