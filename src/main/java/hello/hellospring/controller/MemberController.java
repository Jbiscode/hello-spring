package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
}
