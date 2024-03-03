package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
// import hello.hellospring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// 통합 테스트
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  //*  필요없다 (MemoryMemberRepository를 사용하지 않기 때문 그리고 @Transactional 때문에 롤백이 되기 때문에)
  // @AfterEach
  // void tearDown() {
  //   memberRepository.clear();
  // }

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("John");

    // when
    Long savedId = memberService.join(member);

    // then
    Member foundMember = memberService.findOne(savedId).orElse(null);
    assertThat(foundMember.getName()).isEqualTo(member.getName());
  }

  @Test
  void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("John");

    Member member2 = new Member();
    member2.setName("John");

    // when
    memberService.join(member1);

    // then

    // 둘중 하나를 사용해도 됨

    // assertThatThrownBy(() -> memberService.join(member2))
    //     .isInstanceOf(IllegalStateException.class)
    //     .hasMessage("이미 존재하는 회원입니다.");

    assertThrows(
      IllegalStateException.class,
      () -> memberService.join(member2)
    );
  }

  @Test
  void 전체_회원_조회() {
    // given
    Member member1 = new Member();
    member1.setName("John");
    memberService.join(member1);

    Member member2 = new Member();
    member2.setName("Jane");
    memberService.join(member2);

    // when
    List<Member> members = memberService.findMembers();

    // then
    assertThat(members).hasSize(members.size());
    assertThat(members).contains(member1, member2);
  }

  @Test
  void 회원_조회() {
    // given
    Member member = new Member();
    member.setName("John");
    Long savedId = memberService.join(member);

    // when
    Optional<Member> foundMember = memberService.findOne(savedId);

    // then
    assertThat(foundMember).isPresent();
    assertThat(foundMember.get()).isEqualTo(member);
  }
}
