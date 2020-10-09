package by.tech.messageapp.main;

public class InputValidator {

	public static String validateText(String text) {
		if (text.length() > 200) {
			text = text.substring(0, 200);
		}
		return text;
	}

	public static String validateType(String type) {
		if (!(type.equals("no") || type.equals("pic") || type.equals("doc") || type.equals("audio"))) {
			type = "not exists";
		}
		return type;
	}

}
