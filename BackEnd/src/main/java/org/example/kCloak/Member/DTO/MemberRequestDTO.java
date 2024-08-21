package org.example.test.Member.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {
    @NotBlank(message = "memberName can't Blank")
    private String memberName;
    @NotBlank(message = "memberEmail can't Blank")
    private String memberEmail;
    @NotBlank(message = "memberPwd can't Blank")
    private String memberPwd;
}
