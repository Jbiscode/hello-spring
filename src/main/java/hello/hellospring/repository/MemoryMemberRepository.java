package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;
@Repository
public class MemoryMemberRepository implements MemberRepository {

  private Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public List<Member> findAll() {
    return List.copyOf(store.values());
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store
      .values()
      .stream()
      .filter(member -> member.getName().equals(name))
      .findAny();
  }

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  public void clear() {
    store.clear();
  }
}
