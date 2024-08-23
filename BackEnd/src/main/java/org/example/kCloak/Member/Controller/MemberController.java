package org.example.kCloak.Member.Controller;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Member.DTO.MemberRequestDTO;
import org.example.kCloak.Member.DTO.MemberResponseDTO;
import org.example.kCloak.Member.Service.MemberService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDTO> memberWrite(@RequestBody @Valid MemberRequestDTO request){
        return ResponseEntity.ok().body(
                memberService.saveMember(request)
        );
    }

    @GetMapping("/okok")
    public String okok(){
        return "okok";
    }

    @GetMapping("/test")
    public ResponseEntity<MemberResponseDTO> test(Principal principal){
        return ResponseEntity.ok().body(new MemberResponseDTO(
                200,
                "성공"
        ));
    }
}
