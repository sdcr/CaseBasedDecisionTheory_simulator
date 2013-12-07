package cbdt.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LogManager encapsulates the functionality related to logging application events.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class LogManager {
	
	/**
	 * Initializes the logging mechanism. This implementation sets the out and
	 * the err stream of System to a PrintStream, which prints to a log file.
	 */
	public void initLogging(){
		PrintStream logOut = null;
		try {
			logOut = createLoggingPrintStream();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(logOut);
		System.setErr(logOut);
	}

	/**
	 * Sets up a {@link PrintStream} to a file in the log/ directory, named
	 * according to the current date.
	 * 
	 * @return the set up {@link PrintStream}
	 * @throws FileNotFoundException
	 */
	private PrintStream createLoggingPrintStream()
			throws FileNotFoundException {
		DateFormat df = new SimpleDateFormat("dd-MM-y_HH-mm-ss-S");
		String formattedDate = df.format (new Date ());

		File logFile = new File("./log/cbdt_"+formattedDate+".log");
		File logDirectory = new File("./log");
		if(!logDirectory.exists())
			logDirectory.mkdir();
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintStream printStream = new PrintStream(logFile);
		printStream.println("SESSION date: "+formattedDate);
		printStream.println("------------------------------------------------");
		
		return printStream;
	}
}
