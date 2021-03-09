package com.library.study.demo.service;

import com.library.study.demo.domain.Member;
import com.library.study.demo.domain.User;
import com.library.study.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService implements UserService{

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public User join(User user) {
        Member member = (Member) user;
        return memberRepository.save(member);
    }


}
