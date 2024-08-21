package org.example.test.Member.Repository;

import org.example.test.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberEmail(String memberEmail);

    Optional<Member> findByMemberName(String memberName);
}
