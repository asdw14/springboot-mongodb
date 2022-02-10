package com.dizhongdi.mongodb;

import com.dizhongdi.mongodb.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
class SpringbootMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setAge(13);
        user.setEmail("275506393@qq.com");
        user.setName("zhangsan");
        mongoTemplate.insert(user);
//        查所有
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }
    //根据id查询
    @Test
    public void getById() {
        User user =
                mongoTemplate.findById("620528cd0f54c7029a6f3e8c", User.class);
        System.out.println(user);
    }
    //条件查询
    @Test
    public void findUserList() {
        Query query = new Query(
                Criteria.where("name").is("弟中弟").
                        and("age").is(18));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }
    //模糊查询
    @Test
    public void findUsersLikeName() {
        String name = "z";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Query query = new Query(
                Criteria.where("name").regex(pattern));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);

    }

    //分页查询
    @Test
    public void findUsersPage() {
        String name = "中";
        int pageNo = 1;
        int pageSize = 10;

        Query query = new Query();
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));
        int totalCount = (int) mongoTemplate.count(query, User.class);
        List<User> userList = mongoTemplate.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", userList);
        pageMap.put("totalCount",totalCount);
        System.out.println(pageMap);
    }

    //修改
    @Test
    public void updateUser() {
        User user = mongoTemplate.findById("62053d84d9d55a61684bee19", User.class);
        user.setName("弟中弟修改").setEmail("2755063993@qq.com");

        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("name",user.getName());
        update.set("email",user.getEmail());
        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
        long count = upsert.getMatchedCount();
        System.out.println(count);
    }
    //删除操作
    @Test
    public void delete() {
        Query query = new Query(Criteria.where("name").is("zhangsan"));
        DeleteResult remove = mongoTemplate.remove(query, User.class);
        long count = remove.getDeletedCount();
        System.out.println(count);
    }
    

}
