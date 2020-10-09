package by.tech.messageapp.service;

import java.util.List;

import by.tech.messageapp.entity.Message;

public interface MessageService {

	String sendMessages(List<Message> messages);

}