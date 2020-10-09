package by.tech.messageapp.service;

import java.util.Iterator;
import java.util.List;

import by.tech.messageapp.entity.AppChannel;
import by.tech.messageapp.entity.Email;
import by.tech.messageapp.entity.Facebook;
import by.tech.messageapp.entity.Message;
import by.tech.messageapp.entity.Request;
import by.tech.messageapp.entity.Sms;

public class MessageServiceImpl implements MessageService {

	private static final String UNSUCCESS_SEND_MESSAGE = "Messages were not sent. Messages should be created.";
	private RequestFactory requestFactory;

	public MessageServiceImpl() {
		this.requestFactory = new RequestFactory();
	}

	public String sendMessages(List<Message> messageList) {
		if ((messageList == null) || (messageList.size() == 0)) {
			return UNSUCCESS_SEND_MESSAGE;
		} else {
			StringBuilder result = new StringBuilder("Sent \n");
			for (Message message : messageList) {
				List<AppChannel> channels = message.getChannels();
				if (channels == null || channels.size() == 0) {
					continue;
				}
				for (AppChannel channel : channels) {
					Request request = requestFactory.createRequest(message, channel);
					result.append(sendRequest(request, channel));
				}
			}
			Iterator<Message> iterator = messageList.iterator();
			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}
			return result.toString();
		}
	}

	private String sendRequest(Request request, AppChannel appChannel) {
		if (appChannel instanceof Email) {
			Email email = (Email) appChannel;
			return "Email: " + email.getAddress() + ", " + request.getMessage().toString() + "\n";
		} else if (appChannel instanceof Facebook) {
			Facebook facebook = (Facebook) appChannel;
			return "Facebook: userId " + facebook.getUserId() + ", " + request.getMessage().toString() + "\n";
		} else if (appChannel instanceof Sms) {
			Sms sms = (Sms) appChannel;
			return "Sms: phone " + sms.getPhone() + ", " + request.getMessage().toString() + "\n";
		} else {
			throw new IllegalArgumentException("No such channel " + appChannel);
		}
	}
}