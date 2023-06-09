package com.spring.blog.controller;

import com.spring.blog.web.dto.BmiDTO;
import com.spring.blog.web.dto.PersonDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //컨트롤러로 지정
//@ResponseBody   //REST 형식전환, 메서드 위에 붙으면 해당 메서드만 REST형식으로
@RequestMapping("/resttest")
@CrossOrigin(origins = "http://127.0.0.1:5000") //해당 출처의 비동기요청 허용
//서버는 일반적으로 확인되지 않은 출처의 요청은 받아들이지 않는다.
//@CrossOrigin 을 하면 해당 서버(현재기준 Live server)에서 들어오는 요청은 받아들이라고 설정한 것.

public class RESTApiController {
    //REST controller는 크게 json을 리턴하거나, String을 리턴하게 만들 수 있다.
    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    //문자 배열도 리턴할 수 있습니다.
//    @RequestMapping(value = "/foods", method = RequestMethod.GET)
    @GetMapping("/foods")
    public List<String> foods(){
        List<String> foodList = List.of("hamburger", "hotdog", "cheesecake");
        return foodList;
    }
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public PersonDTO person(){
        PersonDTO p = PersonDTO.builder()
                .id(1L).name("Mila").age(20).build();
        return p;
    }

    //상태코드까지 함께 리턴할 수 있는 ResponseEntity<>를 리턴 자료형으로 지정
    @GetMapping("/person-list")
    //어떤 자료가 오든 상관 없을 때 ?준다.
    public ResponseEntity<?> personList(){
        PersonDTO p = PersonDTO.builder()
                .id(1L).name("Mila").age(29).build();
        PersonDTO p2 = PersonDTO.builder()
                .id(2L).name("Bori").age(3).build();
        PersonDTO p3 = PersonDTO.builder()
                .id(3L).name("Jason").age(37).build();

        List<PersonDTO> personList = List.of(p, p2, p3);
        //.ok()는 200 코드를 반환하고, 뒤에 연달아 붙은 body()에 리턴하고자 하는 자료를 입력한다.
        return ResponseEntity.ok().body(personList);
    }

    @RequestMapping(value = "/bmi", method = RequestMethod.GET)
    public ResponseEntity<?> bmi(BmiDTO bmi){
        //예외처리 들어갈 자리
        //ResponseEntity를 이용해 시나리오대로 잘 돌아가면 .ok를 주면 되는거고, 예외처리를 따로 해줄 수 있다.
        if(bmi.getHeight() == 0){
            return ResponseEntity
                    .badRequest()   //400
                    .body("height에 0을 넣지 말고 제대로 된 값을 넣어주세요");
        }

        double result = bmi.getHeight()  / ((bmi.getHeight()/100) * (bmi.getHeight()/100));

        //헤더 추가
        //헤더 없이 바디만 리턴해도 무관하다.
        HttpHeaders headers = new HttpHeaders();
        //key-value 쌍을 추가해서 header에 적재
        headers.add("fruits", "melon");
        headers.add("lunch", "pizza");

        return ResponseEntity
                .ok()   //200
                .headers(headers)   //헤더추가
                .body(result);   //사용자에게 보여질 데이터
    }

    //Postman을 활용한 json 데이터 파라미터오 전송해 요청 넣기(역직렬화)
    //서버 대 서버로 요청을 주고 받거나 각기 다른 언어를 가진 플랫폼끼리 RESTful 하게 주고받을 때
    //Body - raw - JSON
    /*
    {
    "height":175.1,
    "weight":64.3
    }
    */
    @RequestMapping(value = "/bmi2", method = RequestMethod.GET)
    //@RequestBody: 요청을 보낼때 파라미터 값을 JSON/XML으로 보내주세요.
    public ResponseEntity<?> bmi2(@RequestBody BmiDTO bmi){
        //예외처리 들어갈 자리
        //ResponseEntity를 이용해 시나리오대로 잘 돌아가면 .ok를 주면 되는거고, 예외처리를 따로 해줄 수 있다.
        if(bmi.getHeight() == 0){
            return ResponseEntity
                    .badRequest()   //400
                    .body("height에 0을 넣지 말고 제대로 된 값을 넣어주세요");
        }

        double result = bmi.getHeight()  / ((bmi.getHeight()/100) * (bmi.getHeight()/100));

        //헤더 추가
        //헤더 없이 바디만 리턴해도 무관하다.
        HttpHeaders headers = new HttpHeaders();
        //key-value 쌍을 추가해서 header에 적재
        headers.add("fruits", "melon");
        headers.add("lunch", "pizza");

        return ResponseEntity
                .ok()   //200
                .headers(headers)   //헤더추가
                .body(result);   //사용자에게 보여질 데이터
    }
}
