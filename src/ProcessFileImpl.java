import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessFileImpl implements ProcessFile {
	
	public static final int LOWER_UPPER_ALPHABET_DIFF = 32;
	final Logger LOGGER = Logger.getLogger(ProcessFileImpl.class.getName());
	
	public List<String> readFile(ArrayList<String> filePaths) throws IOException, URISyntaxException {
		HashSet<String> set1 = new HashSet<String>();
		HashSet<String> set2 = new HashSet<String>();
		BufferedReader reader = null;

		try {
			if (filePaths.size() < 3) {
				throw new Exception("Need at least 3 files");
			}
			//BufferedReader reader;
			String line;
			String[] words;
						boolean firstFile = true;
			for (int i=0; i< filePaths.size(); i++) {
				reader = new BufferedReader(new FileReader(filePaths.get(i)));
				line = reader.readLine();
				// creating hashset for first file
				if (firstFile) {
					while (null != line && !line.isEmpty()) {
						words = line.split(" ");
						//set1.addAll(Arrays.asList(words));
						for (int j=0; j<words.length; j++) {
							String word = sanitize(words[j]);
							set1.add(word);
						}
						line = reader.readLine();
					}
					firstFile = false;
				} else {
					//using set2 with subsequent files
					while(null != line && !line.isEmpty()) {
						words = line.split(" ");
						for (int j=0; j<words.length; j++) {
							String word = sanitize(words[j]);
							if (set1.contains(word)) {
								set2.add(word);
							}
						}
						line = reader.readLine();
					}
					set1 = set2;
					set2 = new HashSet<String>();
					//System.out.println("Common words after file " + (i+1) + " are: " + set1);
					LOGGER.log(Level.INFO, "Common words after file " + i+1 + " are: " + set1);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return new ArrayList<String>(set1);
	}
	
	private String sanitize(String s) {
		int len = s.length();
		char[] word = new char[len];
		int k=0;
		for (int i=0; i<len; i++) {
			if ((int)s.charAt(i) > 64 && (int)s.charAt(i) < 91) {
				word[k++] = (char)(((int)s.charAt(i))+LOWER_UPPER_ALPHABET_DIFF);
			} else if ((int)s.charAt(i) > 96 && (int)s.charAt(i) < 123) {
				word[k++] = s.charAt(i);
			}
		}
		return getString(word, k);//(Arrays.copyOf(word, k)).toString();
	}
	
	private String getString(char[] word, int len) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<len; i++) {
			sb.append(word[i]);
		}
		return sb.toString();
	}
}
