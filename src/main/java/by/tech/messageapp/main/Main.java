package by.tech.messageapp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		CommandExecutor commandExecutor = new CommandExecutor();
		MessagePrinter messagePrinter = new MessagePrinter();
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String command;
		String response;
		System.out.println("Welcome to MessageApp!");
		try {
			do {
				System.out.println("-------------------------");
				System.out.println("Print 1 to create Messages");
				System.out.println("Print 2 to send Messages");
				System.out.println("Print 0 to close the app");
				System.out.println("-------------------------");
				command = bufferedReader.readLine();
				command = command.trim();
				response = commandExecutor.execute(command, bufferedReader);
				messagePrinter.printResponse(response);
			} while (!command.equals("0"));
		} catch (IOException e) {
			messagePrinter.printResponse("Error is occured!");
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					messagePrinter.printResponse("Error is occured!");
				}
			}
		}
	}

}