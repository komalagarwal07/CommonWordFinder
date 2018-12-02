import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * This file does the following:
 * <ul>
 * <li>Reads file for the input file paths</li>
 * <li>Remove special characters for all the words</li>
 * <li>Extract common words from all the files and return back</li>
 * </ul>
 * 
 * @author komal
 * @since 01/12/2018
 */

public class CommonWordsFinderImpl implements CommonWordsFinder {
	
	private static final int LOWER_UPPER_ALPHABET_DIFF = 32;
	private static final int ASCII_VALUE_UPPER_A = 65;
	private static final int ASCII_VALUE_UPPER_Z = 90;
	private static final int ASCII_VALUE_LOWER_A = 97;
	private static final int ASCII_VALUE_LOWER_Z = 122;
	private static final Logger LOGGER = Logger.getLogger(CommonWordsFinderImpl.class.getName());
	
	public List<String> commonWordsProcessor(List<String> filePaths) throws IOException, URISyntaxException {
		
		if (null == filePaths || filePaths.isEmpty()) {
			throw new IllegalArgumentException("Invalid file paths given");
		}
		
		Set<String> commonWordsSet = new HashSet<String>();
		Set<String> tempCommonWordsSet = new HashSet<String>();
		BufferedReader reader = null;

		try {
			String line;
			String[] words;
			for (int i=0; i< filePaths.size(); i++) {
				reader = new BufferedReader(new FileReader(filePaths.get(i)));
				line = reader.readLine();
				while (null != line && !line.isEmpty()) {
					words = line.split(" ");
					for (int j=0; j<words.length; j++) {
						String word = sanitizeWord(words[j]);
						// when commonWordsSet is empty, add all words to tempCommonWordsSet, 
						// otherwise check for common words
						if (commonWordsSet.isEmpty() || commonWordsSet.contains(word)) {
							tempCommonWordsSet.add(word);
						}
					}
					line = reader.readLine();
				}
				if (tempCommonWordsSet.isEmpty()) {
					reader.close();
					throw new NoSuchElementException("No common word found!");
				}
				commonWordsSet = tempCommonWordsSet;
				tempCommonWordsSet = new HashSet<String>();
				LOGGER.log(Level.INFO, "Common words after file " + i+1 + " are: " + commonWordsSet);
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
		return new ArrayList<String>(commonWordsSet);
	}
	
	private String sanitizeWord(String s) {
		int len = s.length();
		char[] word = new char[len];
		int k=0;
		for (int i=0; i<len; i++) {
			//if letter, storing it as lower case letter in word array
			if ((int)s.charAt(i) >= ASCII_VALUE_UPPER_A && (int)s.charAt(i) <= ASCII_VALUE_UPPER_Z) {
				// handling upper case letter
				word[k++] = (char)(((int)s.charAt(i))+LOWER_UPPER_ALPHABET_DIFF);
				
			} else if ((int)s.charAt(i) >= ASCII_VALUE_LOWER_A && (int)s.charAt(i) <= ASCII_VALUE_LOWER_Z) {
				//handling lower case letter
				word[k++] = s.charAt(i);
				
			}
		}
		return createStringFromCharArray(word, k);
	}
	
	private String createStringFromCharArray(char[] word, int len) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<len; i++) {
			sb.append(word[i]);
		}
		return sb.toString();
	}
}
