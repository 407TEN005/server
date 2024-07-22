package sixgarlic.potenday.test.model;

import jakarta.persistence.*;
import sixgarlic.potenday.member.model.Member;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    private String question;

    @Enumerated(EnumType.STRING)
    private AnswerType answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
