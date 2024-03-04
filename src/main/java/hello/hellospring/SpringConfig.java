package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // memberRepository() 메서드를 호출해서 MemoryMemberRepository를 스프링 빈에 등록한다.
    // @Bean
    // MemberRepository memberRepository() {
    //   return new MemoryMemberRepository();
    // }


    // memberRepository() 메서드를 호출해서 JdbcMemberRepository를 스프링 빈에 등록한다.
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JdbcMemberRepository는 DataSource를 주입받아야 하므로 생성자를 통해 주입받는다.
//  @Bean
//  MemberRepository memberRepository() {
//    return new JdbcMemberRepository(dataSource);
//  }

//    private final EntityManager em;

//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // JdbcTemplateMemberRepository는 DataSource를 주입받아야 하므로 생성자를 통해 주입받는다.
//    @Bean
//    MemberRepository memberRepository() {
//         return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }


    // memberService() 메서드를 호출해서 MemberService를 스프링 빈에 등록한다.
    @Bean
    MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
