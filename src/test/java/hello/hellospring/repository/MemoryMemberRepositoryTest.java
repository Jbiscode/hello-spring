package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * MemoryMemberRepositoryTest는 MemoryMemberRepository 클래스의 테스트를 위한 테스트 클래스입니다.
 */
class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository;

  /**
   * 각 테스트 메서드 실행 전에 호출되는 메서드입니다.
   * MemoryMemberRepository 객체를 생성하여 repository 필드에 할당합니다.
   */
  @BeforeEach
  public void setUp() {
    repository = new MemoryMemberRepository();
  }

  /**
   * 각 테스트 메서드 실행 후에 호출되는 메서드입니다.
   * repository의 데이터를 초기화합니다.
   */
  @AfterEach
  public void afterEach() {
    repository.clear();
  }

  /**
   * save 메서드는 회원을 저장하는 기능을 테스트합니다.
   */
  @Test
  public void save() {
    // Given
    Member member = new Member();
    member.setName("John Doe");

    // When
    Member savedMember = repository.save(member);

    // Then
    Member foundMember = repository.findById(savedMember.getId()).orElse(null);
    assertThat(foundMember).isEqualTo(savedMember);
  }

  /**
   * findById 메서드는 회원을 ID로 조회하는 기능을 테스트합니다.
   */
  @Test
  public void findById() {
    // Given
    Member member1 = new Member();
    member1.setName("John Doe");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("Jane Smith");
    repository.save(member2);

    // When
    Optional<Member> foundMember = repository.findById(member1.getId());

    // Then
    assertThat(foundMember).isPresent();
    assertThat(foundMember.get()).isEqualTo(member1);

    foundMember = repository.findById(member2.getId());
    assertThat(foundMember).isPresent();
    assertThat(foundMember.get()).isEqualTo(member2);
  }

  /**
   * findByName 메서드는 회원을 이름으로 조회하는 기능을 테스트합니다.
   */
  @Test
  public void findByName() {
    // Given
    Member member1 = new Member();
    member1.setName("John Doe");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("Jane Smith");
    repository.save(member2);

    // When
    Optional<Member> foundMember = repository.findByName("John Doe");

    // Then
    assertThat(foundMember).isPresent();
    assertThat(foundMember.get()).isEqualTo(member1);
  }

  /**
   * findAll 메서드는 모든 회원을 조회하는 기능을 테스트합니다.
   */
  @Test
  public void findAll() {
    // Given
    Member member1 = new Member();
    member1.setName("John Doe");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("Jane Smith");
    repository.save(member2);

    // When
    List<Member> members = repository.findAll();

    // Then
    assertThat(members).hasSize(2);
    assertThat(members).contains(member1, member2);
    assertThat(members.size()).isEqualTo(2);
  }
}
