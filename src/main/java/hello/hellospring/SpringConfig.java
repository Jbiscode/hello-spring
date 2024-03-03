package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  // memberRepository() 메서드를 호출해서 MemoryMemberRepository를 스프링 빈에 등록한다.
  // @Bean
  // MemberRepository memberRepository() {
  //   return new MemoryMemberRepository();
  // }


  // memberRepository() 메서드를 호출해서 JdbcMemberRepository를 스프링 빈에 등록한다.
  private final DataSource dataSource;

  public SpringConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  MemberRepository memberRepository() {
    return new JdbcMemberRepository(dataSource);
  }

  // memberService() 메서드를 호출해서 MemberService를 스프링 빈에 등록한다.
  @Bean
  MemberService memberService() {
    return new MemberService(memberRepository());
  }
}
