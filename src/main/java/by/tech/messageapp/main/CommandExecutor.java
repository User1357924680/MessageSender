package by.tech.messageapp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.tech.messageapp.entity.AppChannel;
import by.tech.messageapp.entity.Attachment;
import by.tech.messageapp.entity.Email;
import by.tech.messageapp.entity.Facebook;
import by.tech.messageapp.entity.Message;
import by.tech.messageapp.entity.Sms;
import by.tech.messageapp.service.MessageService;
import by.tech.messageapp.service.MessageServiceImpl;

public class CommandExecutor {

	private static final String EMPTYCOMMAND_MESSAGE = "The command doesn't exist";
	private static final String CLOSE_MESSAGE = "The app is closed";
	private static final String SUCCESS_MESSAGE = "Messages were created";
	private static final String UNSUCCESS_MESSAGE = "Messages were not created";
	private static final String ATTACHEMENTS_COMMAND = "Select(print) ATTACHMENTS type: "
			+ "\"pic\" - picture, \"doc\" - document, \"audio\" - audio,\n"
			+ "\"no\" - if there are no any attachments: ";
	private static final String CHANNELS_COMMAND = "Select(print) CHANNELS to send message: "
			+ "\"f\" - facebook, \"e\" - email, \"s\" - sms.\n" + "E.g. print fs to select facebook and sms. \n";
	private static final String CREATE_FACEBOOK = "Facebook channel is creating. Enter userId: ";
	private static final String CREATE_EMAIL = "Email channel is creating. Enter address: ";
	private static final String CREATE_SMS = "SMS channel is creating. Enter phoneNumber: ";
	private static final String PRINT_ATTACHEMENT_NAME = "Print attachment name: ";
	private static final String ENTER_USERNAME = "Enter UserName or print \"back\" to go to main menu: ";
	private static final String ENTER_TEXT = "Enter text(200 characters or less): ";

	private MessageService messageService;
	private List<Message> messages;
	private int counter = 1;

	public CommandExecutor() {
		this.messageService = new MessageServiceImpl();
		this.messages = new ArrayList<Message>();
	}

	public String execute(String command, BufferedReader reader) throws IOException {
		String response;
		switch (command) {
		case "1":
			response = createMessages(reader);
			break;
		case "2":
			response = sendMessages();
			break;
		case "0":
			response = CLOSE_MESSAGE;
			break;
		default:
			response = EMPTYCOMMAND_MESSAGE;
		}
		return response;
	}

	public String createMessages(BufferedReader reader) throws IOException {
		String userName;
		String text;
		do {
			System.out.println("Message " + counter);
			userName = enterUserName(reader);
			if (!userName.equals("back")) {
				Message message = new Message();
				message.setId(counter);
				message.setUserName(userName);
				message.setDate(LocalDate.now());
				text = enterText(reader);
				message.setText(text);
				List<Attachment> attachments = new ArrayList<>();
				attachments = selectAttachments(reader);
				message.setAttachements(attachments);
				List<AppChannel> channels = createChannels(reader);
				message.setChannels(channels);
				messages.add(message);
				counter++;
			}
		} while (!userName.equals("back"));
		if (messages.size() != 0) {
			return SUCCESS_MESSAGE;
		} else {
			return UNSUCCESS_MESSAGE;
		}
	}

	private List<AppChannel> createChannels(BufferedReader reader) throws IOException {
		List<AppChannel> channels = new ArrayList<>();
		String type;
		do {
			System.out.print(CHANNELS_COMMAND);
			type = reader.readLine();
			type = type.trim();
		} while (type == null || type.isEmpty());
		type.replaceAll("\\s+", "");
		char[] types = type.toCharArray();
		for (char channelType : types) {
			AppChannel channel = createChannel(channelType, reader);
			channels.add(channel);
		}
		return channels;
	}

	private AppChannel createChannel(char type, BufferedReader reader) throws IOException {
		AppChannel appChannel = null;
		String inputString;
		switch (type) {
		case 'f':
			System.out.print(CREATE_FACEBOOK);
			inputString = reader.readLine();
			inputString = inputString.trim();
			appChannel = new Facebook(inputString);
			break;
		case 'e':
			System.out.print(CREATE_EMAIL);
			inputString = reader.readLine();
			inputString = inputString.trim();
			appChannel = new Email(inputString);
			break;
		case 's':
			System.out.print(CREATE_SMS);
			inputString = reader.readLine();
			inputString = inputString.trim();
			appChannel = new Sms(inputString);
			break;
		}
		return appChannel;
	}

	private String sendMessages() {
		return messageService.sendMessages(messages);
	}

	private String enterUserName(BufferedReader reader) throws IOException {
		String name;
		do {
			System.out.print(ENTER_USERNAME);
			name = reader.readLine();
			name = name.trim();
		} while (name == null || name.isEmpty());
		return name;
	}

	private String enterText(BufferedReader reader) throws IOException {
		String text;
		do {
			System.out.print(ENTER_TEXT);
			text = reader.readLine();
			text = text.trim();
			text = InputValidator.validateText(text);
		} while (text == null || text.isEmpty());
		return text;
	}

	private List<Attachment> selectAttachments(BufferedReader reader) throws IOException {
		List<Attachment> attachments = new ArrayList<>();
		String name;
		String type;
		do {
			System.out.print(ATTACHEMENTS_COMMAND);
			type = reader.readLine();
			type = type.trim();
			type = InputValidator.validateType(type);
			if (!type.equals("no") && !(type.equals("not exists"))) {
				do {
					System.out.print(PRINT_ATTACHEMENT_NAME);
					name = reader.readLine();
					name = name.trim();
					if (!(name == null || name.isEmpty())) {
						Attachment attachment = new Attachment(name, type);
						attachments.add(attachment);
					}
				} while (name == null || name.isEmpty());
			}
		} while (!type.equals("no"));
		return attachments;
	}

}