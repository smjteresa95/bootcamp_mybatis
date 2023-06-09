package com.spring.blog.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @Builder
@NoArgsConstructor @ToString

public class BmiDTO {
    private double height;
    private double weight;
}
