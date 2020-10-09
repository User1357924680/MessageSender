package by.tech.messageapp.service;

import java.util.ArrayList;
import java.util.List;

import by.tech.messageapp.entity.AppChannel;
import by.tech.messageapp.entity.Attachment;
import by.tech.messageapp.entity.Email;
import by.tech.messageapp.entity.EmailRequest;
import by.tech.messageapp.entity.Facebook;
import by.tech.messageapp.entity.FacebookRequest;
import by.tech.messageapp.entity.Message;
import by.tech.messageapp.entity.Request;
import by.tech.messageapp.entity.Sms;
import by.tech.messageapp.entity.SmsRequest;

public class RequestFactory {

	public Request createRequest(Message message, AppChannel channel) {

		if (channel instanceof Email) {
			Message emailMessage = new Message(message.getId(), message.getUserName(), message.getDate(), message.getText(),
					message.getAttachments());
			return new EmailRequest(emailMessage);
		} else if (channel instanceof Facebook) {
			List<Attachment> attachmentsForFacebook = message.getAttachments();
			attachmentsForFacebook = filterAttachments(attachmentsForFacebook);
			Message facebookMessage = new Message(message.getId(), message.getUserName(), message.getDate(), message.getText(),
					attachmentsForFacebook);
			return new FacebookRequest(facebookMessage);
		} else if (channel instanceof Sms){
			Message smsMessage = new Message(message.getId(), message.getUserName(), message.getDate(), message.getText());
			return new SmsRequest(smsMessage);
		} else {
			throw new IllegalArgumentException("Unknown channel " + channel);
		}
	}

	private List<Attachment> filterAttachments(List<Attachment> attachmentsForFacebook) {
		List<Attachment> attachments = new ArrayList<Attachment>();
		for (Attachment attachment : attachmentsForFacebook) {
			if (!attachment.getType().equals("audio")) {
				attachments.add(attachment);
			}
		}
		return attachments;
	}
}
