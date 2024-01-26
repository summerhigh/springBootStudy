package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
  // 생성자로 스프링 컨테이너에 memberService 객체를 한번 등록해두면
  // 객체를 여러개 생성하지 않고도 다른 컨트롤러에서도 쉽게 불러다 쓸 수 있다. -> 스프링빈으로 등록한 것이기 때문
  // Autowired라고 어노테이션을 붙여놓아야 함

  // @Service + @Controller + @Repository를 다 각각 붙여놔야
  // 스프링이 어노테이션으로 매핑을 해준다. (해당 패키지 내에서)

  @GetMapping("members/new")
  public String createForm(){
    return "members/createMemberForm";
  }

  @PostMapping("members/new")
  public String create(MemberForm form){
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);
    return "redirect:/";
  }
}
