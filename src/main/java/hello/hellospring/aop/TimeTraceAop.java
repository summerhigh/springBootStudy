package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

  @Around("execution(* hello.hellospring..*(..))")  // 적용할 패키지를 지정하는 어노테이션
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
    long start = System.currentTimeMillis();
    System.out.println("START : " + joinPoint.toString());
    try{
      return joinPoint.proceed();
    }finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
    }
  }
  // 지정한 패키지에서 메소드를 호출할 떄마다 이 AOP가 먼저 인터셉트되어 작동을 하는 구조
}
