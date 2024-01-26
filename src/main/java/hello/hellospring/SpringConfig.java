package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
/*
  // JpaMemberRepository
  private EntityManager em;

  @Autowired
  public SpringConfig(EntityManager em) {
    this.em = em;
  }
*/

 /*
 // JdbcTemplateMemberRepository
  private final DataSource dataSource;

  public SpringConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }
*/

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository);
    // memberService 객체 생성할 때 memberRepository가 파라미터로 들어가야 함을 명시해줘야 함
  }

//  @Bean
//  public MemberRepository memberRepository(){
//    return new MemoryMemberRepository();
//    return new JdbcTemplateMemberRepository(dataSource);
//    return new JpaMemberRepository(em);
//  }

/*  @Bean
  public TimeTraceAop timeTraceAop(){
    return new TimeTraceAop();
  }*/
  // 그냥 TimeTraceAop에 @Component를 붙여서 컴포넌트 스캔하도록 해도 되지만
  // AOP를 사용한다는것을 다른 개발자가 확인하기 쉽도록 이렇게 Bean을 등록해주는 것이 더 좋다.
}
