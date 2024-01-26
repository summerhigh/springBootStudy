package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

  private final EntityManager em;

  public JpaMemberRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Member save(Member member) {
    em.persist(member);   // 이렇게 하면 JPA가 인서트 쿼리 짜서 디비에 집어넣어줌
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    Member member = em.find(Member.class, id);  // Member클래스에서 id를 찾아서 객체 생성
    return Optional.ofNullable(member);
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> result =  em.createQuery("select m from Member m where m.name= :name", Member.class)
        .setParameter("name",name)
        .getResultList();
    // List로 쿼리 조회 결과를 다 받은 다음, stream()을 이용해 값 하나만 반환한다.
    return result.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class).getResultList();
    // select * 이 아니라 m 이라는 Member 객체 자체임을 주의!
  }
}
