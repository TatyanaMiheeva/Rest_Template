package org.example;

import org.example.config.MyConfig;
import org.example.user.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<User> allUsers = communication.getAllUser();
        System.out.println(allUsers);

        String cookie = communication.getCookie();
        System.out.println(cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);

        User user = new User(3L, "James", "Brown", (byte) 33);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        String s1 = communication.saveUser(httpEntity);

        User user1 = new User(3L, "Thomas", "Shelby", (byte) 33);
        HttpEntity<User> httpEntity1 = new HttpEntity<>(user1, headers);
        String s2 = communication.updateUser(httpEntity1);

        HttpEntity<User> httpEntity2 = new HttpEntity<>(user1, headers);
        String s3 = communication.deleteUser(3L, httpEntity2);

        System.out.println(s1 + s2 + s3);
    }
}
