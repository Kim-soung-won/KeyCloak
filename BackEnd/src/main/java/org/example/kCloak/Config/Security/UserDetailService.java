package org.example.kCloak.Config.Security;


import lombok.RequiredArgsConstructor;
import org.example.kCloak.Config.Security.DTO.CustomUserDetails;
import org.example.kCloak.Member.Entity.Member;
import org.example.kCloak.Member.Repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username::::::::::::::::::::::::::::::::"+username);
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(null);
        System.out.println("id::::::::::::::::::::::::::::::::::"+member.getMemberEmail());
        System.out.println("11111111111111111111111111111111111");
        return new CustomUserDetails(member);
    }
}
