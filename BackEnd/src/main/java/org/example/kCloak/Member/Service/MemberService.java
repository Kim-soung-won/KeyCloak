package org.example.kCloak.Member.Service;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Member.DTO.MemberRequestDTO;
import org.example.kCloak.Member.DTO.MemberResponseDTO;
import org.example.kCloak.Member.Entity.Member;
import org.example.kCloak.Member.Repository.MemberRepository;
import org.example.kCloak.Roles.Service.RolesService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesService rolesService;

    @Transactional(readOnly = true)
    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(null);
    }

    @Transactional
    public MemberResponseDTO saveMember(MemberRequestDTO requst){
        if(isEmail(requst.getMemberEmail())){
            return new MemberResponseDTO(123,"이미 존재하는 이메일 입니다.");
        }
        if(isName(requst.getMemberName())){
            return new MemberResponseDTO(123, "이미 존재하는 이름입니다.");
        }

        Member member = Member.builder()
                .memberEmail(requst.getMemberEmail())
                .memberName(requst.getMemberName())
                .memberPwd(passwordEncoder.encode(requst.getMemberPwd()))
                .role(rolesService.findById(3L))
                .build();


        memberRepository.save(member);
        return new MemberResponseDTO(200, "등록에 성공하였습니다.");
    }

    @Transactional(readOnly = true)
    // Email 중복 체크
    public boolean isEmail(String email){
        Member member = memberRepository.findByMemberName(email).orElse(null);
        return member!=null;
    }

    @Transactional(readOnly = true)
    // 이름 중복 체크
    public boolean isName(String name){
        Member member = memberRepository.findByMemberName(name).orElse(null);
        return member!=null;
    }

}
