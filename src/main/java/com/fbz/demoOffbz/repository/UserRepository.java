package com.fbz.demoOffbz.repository;

import com.fbz.demoOffbz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 */
@Repository
public class UserRepository {

    private final  ConcurrentHashMap<Integer ,User> repository=new ConcurrentHashMap<>();
    private  ConcurrentHashMap<Integer,User> s=new ConcurrentHashMap<>();
    private  final  static AtomicInteger idGenerator =new AtomicInteger();
    /**
     * 采用内存型存储方式 ->Map
     */
    /**
     * 保存用户对象{@link User} 对象
     * @param user
     * @return 如果保存成功，返回<code>true</code>
     *          如果失败，返回<code>false</code>
     */
    public boolean save(User user){
        //boolean success=false;

        //ID start 1
        Integer id =idGenerator.incrementAndGet();
        //set ID
        user.setId(id);
        //repository.put(id,user);

        return  repository.put(id,user)==null;

    }
}
