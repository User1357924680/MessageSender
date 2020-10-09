package by.tech.messageapp.entity;

import java.time.LocalDate;
import java.util.List;

public class Message {

	private int id;
	private String userName;
	private LocalDate date;
	private String text;
	private List<Attachment> attachments;
	private List<AppChannel> channels;

	public Message() {

	}

	public Message(int id, String userName, LocalDate date, String text) {
		this.id = id;
		this.userName = userName;
		this.date = date;
		this.text = text;
	}

	public Message(int id, String userName, LocalDate date, String text, List<Attachment> attachments) {
		this.id = id;
		this.userName = userName;
		this.date = date;
		this.text = text;
		this.attachments = attachments;
	}

	public Message(int id, String userName, LocalDate date, String text, List<Attachment> attachments,
			List<AppChannel> channels) {
		this.id = id;
		this.userName = userName;
		this.date = date;
		this.text = text;
		this.attachments = attachments;
		this.channels = channels;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachements(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<AppChannel> getChannels() {
		return channels;
	}

	public void setChannels(List<AppChannel> channels) {
		this.channels = channels;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + ((channels == null) ? 0 : channels.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (channels == null) {
			if (other.channels != null)
				return false;
		} else if (!channels.equals(other.channels))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (attachments == null || attachments.isEmpty()) {
			return "Message [id=" + id + ", userName=" + userName + ", date=" + date + ", text=" + text + "]";
		} else {
			return "Message [id=" + id + ", userName=" + userName + ", date=" + date + ", text=" + text
					+ ", attachments=" + attachments.toString() + "]";
		}
	}
}
