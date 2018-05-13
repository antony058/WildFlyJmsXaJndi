package com.example.weather.brokerproducer.controller;

import com.example.weather.brokerproducer.messageservice.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsController {
    private final JmsProducer jmsProducer;

    @Autowired
    public JmsController(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    @RequestMapping(value = "/send/{text}", method = RequestMethod.GET)
    public String sendMessage(@PathVariable String text) {
        if (text.equals("abc"))
            jmsProducer.sendMessageToTopic(null);
        else
            jmsProducer.sendMessageToTopic(text);

        return "message was send";
    }
}
