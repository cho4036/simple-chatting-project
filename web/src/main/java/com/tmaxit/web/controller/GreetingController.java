package com.tmaxit.web.controller;

import com.tmaxit.web.resource.Greeting;
import com.tmaxit.web.resource.HelloMessage;
import com.tmaxit.web.resource.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); //simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    private List<User> userList = new ArrayList<User>();

    @MessageMapping("/enter")
    @SendTo("/topic/user")
    public User entering(User user) throws Exception {
        Thread.sleep(1000); //simulated delay
        userList.add(user);
        String tmp = "";
        for (int i = 0; i < userList.size(); i++) {
            tmp += userList.get(i).getUser();
        }
        return new User(HtmlUtils.htmlEscape(tmp));
    }
}
