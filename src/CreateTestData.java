import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This file creates sample data to test {@link CommonWordsFinderImpl.java}}
 * 
 * @author komal
 * @since 01.12.2018
 *
 */

public class CreateTestData {
	
	private static final String FILENAME = "/home/komal/eclipse-workspace/AirtelXLabsKomal/file9";

	public static void main(String[] args) {

		BufferedWriter bw = null;

		try {

			String content = "Court has j#ud^*ge who is always cleared with his thoughts and objective.\n";

			bw = new BufferedWriter(new FileWriter(FILENAME));
			for (int i=0; i<8334; i++) {
				bw.write(content);
			}

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				if (bw != null)
					bw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

	}

}
