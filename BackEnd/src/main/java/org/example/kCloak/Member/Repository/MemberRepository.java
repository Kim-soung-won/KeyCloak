package org.example.kCloak.Member.Repository;

import org.example.kCloak.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberEmail(String memberEmail);

    Optional<Member> findByMemberName(String memberName);
}
