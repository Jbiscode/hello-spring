package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemoryMemberRepository memberRepository;
  MemberService memberService;

  @BeforeEach
  void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void tearDown() {
    memberRepository.clear();
  }

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
    assertThat(members).hasSize(2);
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
