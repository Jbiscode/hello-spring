package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

/**
 * MemberRepository 인터페이스는 회원 정보를 저장하고 조회하는 기능을 정의합니다.
 */
public interface MemberRepository {
  Member save(Member member);
  Optional<Member> findById(Long id);
  Optional<Member> findByName(String name);
  List<Member> findAll();
}
