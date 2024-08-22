package org.example.kCloak.Member.Controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.kCloak.Member.DTO.MemberRequestDTO;
import org.example.kCloak.Member.DTO.MemberResponseDTO;
import org.example.kCloak.Member.Entity.Enum.Role;
import org.example.kCloak.Member.Entity.Member;
import org.example.kCloak.Member.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDTO> memberWrite(@RequestBody @Valid MemberRequestDTO request){
        System.out.println(request.getMemberName());
        Member member = Member.builder()
                .memberName(request.getMemberName())
                .memberEmail(request.getMemberEmail())
                .memberPwd(passwordEncoder.encode(request.getMemberPwd()))
                .role(Role.USER)
                .build();


        return ResponseEntity.ok().body(
                memberService.saveMember(member)
        );
    }

    @GetMapping("/test")
    public ResponseEntity<MemberResponseDTO> test(Principal principal){
        return ResponseEntity.ok().body(new MemberResponseDTO(
                200,
                "성공"
        ));
    }
}
