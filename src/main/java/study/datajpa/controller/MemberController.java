package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import study.datajpa.domain.Member;
import study.datajpa.dto.MemberDto;
import study.datajpa.repository.MemberRepository;

@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Member> list(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members;
    }

    @GetMapping("/members2")
    public Page<MemberDto> list2(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }
}
