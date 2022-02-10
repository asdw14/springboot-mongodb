package com.dizhongdi.mongodb.repository;

import com.dizhongdi.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName:UserRepository
 * Package:com.dizhongdi.mongodb.repository
 * Description:
 *
 * @Date: 2022/2/11 1:05
 * @Author:dizhongdi
 */
@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
