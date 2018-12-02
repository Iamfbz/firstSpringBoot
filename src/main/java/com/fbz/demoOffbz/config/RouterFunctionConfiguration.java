package com.fbz.demoOffbz.config;

import com.fbz.demoOffbz.domain.User;
import com.fbz.demoOffbz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import java.util.Collection;

/**
 * 路由器函数配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口：ServletRequest 或者 HttpServletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     *
     * Spring 5.0 重新定义了服务请求和响应接口
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 既可支持Servlet规范，也可以支持自定义，比如Netty(Web Server)
     *
     * 定义Get请求，返回所有用户对象URL: /person/find/all
     *
     * 注入的几种方式：字段注入、Properties注入、Set/Get注入、方法注入、构造器注入
     *
     * Flux 是0—N个对象集合
     * Mono 是1—N个对象集合
     *
     * Reactive 中的Flux或者Mono 它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞)
     *
     * Flux或者Mono 都是publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){

      return  RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
            request ->{
                //返回所有的用户对象
                Collection<User> users = userRepository.findAll();
                //Mono<ServerResponse> response = null;
                Flux<User> userFlux = Flux.fromIterable(users);

                return  ServerResponse.ok().body(userFlux,User.class);

            });

    }
}

//  小伙伴笔记代码
//package com.springboot2.config;
//
//        import com.springboot2.domain.User;
//        import com.springboot2.repository.UserRepository;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.web.reactive.function.server.*;
//        import reactor.core.publisher.Flux;
//
//        import java.util.Collection;
//
///**
// * 路由函数配置类，路由函数可以简单的看作controller中定义的mapping
// * Created By Cx On 2018/5/5 19:45
// */
//@Configuration
//public class RouterFunctionConfiguration {
//
//
//    /**
//     * Web Flux方法实现接口：相比web MVC好处：异步非阻塞，提高吞吐量
//     * 这个方法是函数式编程思想
//     *
//     * Servlet规范中：
//     *      请求接口：ServletRequest 或 HttpServletRequest
//     *      响应接口：ServletResponse 或 HttpServletResponse
//     * 在Spring5.0 重新定义了服务端的请求和响应接口，并支持RouterFunction
//     *      请求接口：ServerRequest
//     *      响应接口：ServerResponse
//     *   优点：既可支持Servlet规范，又可支持自定义，比如Netty（Web Server）
//     *
//     * Flux是0-N的集合（类似于集合类，最少0个，最多N个） Mono是0-1的集合,他们均是Publisher（发布器）类的子类
//     *   为什么不使用集合类和Optional？
//     *     因为 Reactive中 Flux和Mono是异步处理的（非阻塞）    而普通的集合是同步的（阻塞）
//     *
//     * 访问地址是：http://localhost:8080/userList
//     * @return
//     */
//    @Bean
//    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){
//        //参数2是lambda表达式，jdk8以后支持
//        return RouterFunctions.route(RequestPredicates.GET("/userList"),
//                request->{
//                    Collection<User> users = userRepository.findAll();
//                    //将集合转换成Flux
//                    Flux<User> usersFlux = Flux.fromIterable(users);
//                    return ServerResponse.ok().body(usersFlux,User.class);
//                });
//    }
//}