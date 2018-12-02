import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * This 
 * 
 * @author komal
 * @since 01/12/2018
 *
 */
public interface CommonWordsFinder {
	
	List<String> commonWordsProcessor(List<String> filePaths) throws IOException, URISyntaxException;

}
