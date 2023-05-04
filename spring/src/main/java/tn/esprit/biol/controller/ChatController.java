package tn.esprit.biol.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.DestinationVariable;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.biol.dao.ChatDAO;
import tn.esprit.biol.dao.MessageDAO;
import tn.esprit.biol.entity.Chat;
import tn.esprit.biol.entity.Message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/**")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    private MessageDAO messageDAO;


    @MessageMapping("/chat/{to}") //to = nome canale
    public void sendMessage(@DestinationVariable String to , Message message) {
        System.out.println("handling send message: " + message + " to: " + to);
        message.setChat_id(createAndOrGetChat(to));
        message.setT_stamp(generateTimeStamp());
        message = messageDAO.save(message);
        Chat ce = chatDAO.findByName(to);
        ce.setLastMessage(LocalDateTime.now());
        chatDAO.save(ce);

        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);

    }




    @PostMapping("/getChats")


    public List<Chat> getChats(@RequestBody String user){

        List<Chat> ce= chatDAO.findByPartecipant(user);
        List<Chat> sortedList = ce.stream()
                .sorted(Comparator.comparing(Chat :: getLastMessage).reversed())
                .collect(Collectors.toList());
        System.out.println(ce);
        return  sortedList;
    }

    //returns an empty list if the chat doesn't exist
    @PostMapping("/getMessages")

    public List<Message> getMessages(@RequestBody String chat) {
        Chat ce = chatDAO.findByName(chat);

        if(ce != null) {
            return messageDAO.findAllByChat(ce.getChat_id());
        }
        else{
            return new ArrayList<Message>();
        }
    }

    //finds the chat whose name is the parameter, if it doesn't exist it gets created, the ID gets returned either way
    private Long createAndOrGetChat(String name) {
        Chat ce = chatDAO.findByName(name);

        if (ce != null) {
            return ce.getChat_id();
        }
        else {
            Chat newChat = new Chat(name);
            return chatDAO.save(newChat).getChat_id();
        }
    }

    private String generateTimeStamp() {
        Instant i = Instant.now();
        String date = i.toString();
        System.out.println("Source: " + i.toString());
        int endRange = date.indexOf('T');
        date = date.substring(0, endRange);
        date = date.replace('-', '/');
        System.out.println("Date extracted: " + date);
        String time = Integer.toString(i.atZone(ZoneOffset.UTC).getHour() + 1);
        time += ":";

        int minutes = i.atZone(ZoneOffset.UTC).getMinute();
        if (minutes > 9) {
            time += Integer.toString(minutes);
        } else {
            time += "0" + Integer.toString(minutes);
        }

        System.out.println("Time extracted: " + time);
        String timeStamp = date + "-" + time;
        return timeStamp;
    }

}
