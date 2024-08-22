package org.example.kCloak.Member.Service;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Member.DTO.MemberResponseDTO;
import org.example.kCloak.Member.Entity.Member;
import org.example.kCloak.Member.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(null);
    }

    @Transactional
    public MemberResponseDTO saveMember(Member member){
        if(isEmail(member.getMemberEmail())){
            return new MemberResponseDTO(123,"이미 존재하는 이메일 입니다.");
        }
        if(isName(member.getMemberName())){
            return new MemberResponseDTO(123, "이미 존재하는 이름입니다.");
        }
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
