package com.dizhongdi.mongodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ClassName:User
 * Package:com.dizhongdi.mongodb.entity
 * Description:
 *
 * @Date: 2022/2/10 22:48
 * @Author:dizhongdi
 */
@Data
@Document("User")
@Accessors(chain = true)
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String createDate;
}

