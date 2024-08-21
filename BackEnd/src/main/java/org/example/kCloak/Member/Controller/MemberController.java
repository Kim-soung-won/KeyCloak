package org.example.kCloak.Member.Controller;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Member.DTO.MemberRequestDTO;
import org.example.kCloak.Member.DTO.MemberResponseDTO;
import org.example.kCloak.Member.Entity.Member;
import org.example.kCloak.Member.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/write")
    public ResponseEntity<MemberResponseDTO> memberWrite(@RequestBody @Valid MemberRequestDTO request){
        Member member = Member.builder()
                .memberName(request.getMemberName())
                .memberEmail(request.getMemberEmail())
                .memberPwd(request.getMemberPwd())
                .build();


        return ResponseEntity.ok().body(
                memberService.saveMember(member)
        );
    }
}
