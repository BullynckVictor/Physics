package com.physics;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Main
{
	private static String getCurrentDate()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		df.setTimeZone(Calendar.getInstance().getTimeZone());
		return df.format(new Date());
	}

	public static void main(String[] args) {
		MainApp app = null;
		try {
			app = new MainApp();
			app.run();
		}
		catch (Exception e) {
			String filename = "log/error " + getCurrentDate() + ".txt";
			String firstMessage =
					"Fatal exception occurred\n" +
					"Type: " + e.getClass().getCanonicalName() + "\n" +
					"Message: " + e.getMessage();
			boolean successfulWrite;
			try {
				File file = new File("./" + filename);
				PrintStream ps = new PrintStream(file);
				ps.print(firstMessage);
				ps.print('\n');
				ps.print("Stacktrace: ");
				e.printStackTrace(ps);
				successfulWrite = true;
			}
			catch (IOException ioe) {
				successfulWrite = false;
				String s = ioe.toString();
				System.out.println(s);
			}
			System.out.print(firstMessage);
			System.out.print('\n');
			System.out.print("Stacktrace: ");
			e.printStackTrace(System.out);
			String message = firstMessage + "\nStacktrace can be found in standard output" +
					(successfulWrite ? (", as wel as in file ") : (", but failed to write to file")) + "\"" + filename + "\"";
			JOptionPane.showMessageDialog(null,
					message,
					"Fatal Exception",
					JOptionPane.ERROR_MESSAGE
			);
			if (app != null)
				app.dispose();
		}
	}
}
