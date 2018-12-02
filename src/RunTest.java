import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunTest {
	
	public static void main(String[] args) {
		Date start = Calendar.getInstance().getTime();
		ProcessFile processFile = new ProcessFileImpl();
		final Logger LOGGER = Logger.getLogger(RunTest.class.getName());
		
//		String f1 = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file1";
//		String f2 = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file2";
//		String f3 = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file3";
		
		//files with over 1L words each
		String f1 = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file7";
		String f2 = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file8";
		String f3 = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file9";
		ArrayList<String> paths = new ArrayList<String>();
		paths.add(f1);
		paths.add(f2);
		paths.add(f3);
		
		try {
			List<String> result = processFile.readFile(paths);
			if (!result.isEmpty()) {
				System.out.println("result: "+result);
			} else {
				throw new Exception("No common word found!");
			}

		} catch(Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage(), ex.getCause());
			System.out.println(ex.getMessage());
		} finally {
			Date end = Calendar.getInstance().getTime();
			System.out.println(end.getTime()-start.getTime());
		}
		
		
	}

}
