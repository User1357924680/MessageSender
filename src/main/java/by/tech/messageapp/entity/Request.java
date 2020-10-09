package by.tech.messageapp.entity;

public class Request {
	private Message message;

	public Request() {
		
	}

	public Request(Message message) {
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
