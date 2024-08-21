package org.example.kCloak.Member.Entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "member_id")
    private Long memberId;

    @Column(name="member_name", unique = true, nullable = false)
    private String memberName;

    @Column(name="member_email", unique = true, nullable = false)
    private String memberEmail;

    @Column(name="member_pwd", nullable = false)
    private String memberPwd;
}
