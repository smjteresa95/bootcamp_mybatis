package com.spring.blog.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
@ToString
public class PersonDTO {
    private long id;
    private String name;
    private int age;
}
