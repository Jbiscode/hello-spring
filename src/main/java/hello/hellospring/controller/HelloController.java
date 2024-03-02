package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("value", "hello?everyone!");
    return "hello";
  }

  /**
   * helloMvc 메서드는 입력받은 이름을 Model에 추가하고 "hello-template"을 반환합니다.
   *
   * @param name 사용자의 이름
   * @param model 데이터를 전달하기 위한 Model 객체
   * @return "hello-template" 문자열
   */
  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }
}
