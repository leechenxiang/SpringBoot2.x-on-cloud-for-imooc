package com.imooc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("students")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentMO {
    @Id
    private String stuId;

    @Field("name")
    private String name;

    @Field("sex")
    private String sex;

    @Field("age")
    private Integer age;
}
