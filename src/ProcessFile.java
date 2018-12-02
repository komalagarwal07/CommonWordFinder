import java.util.ArrayList;
import java.util.List;

public interface ProcessFile {
	
	public List<String> readFile(ArrayList<String> filePaths) throws Exception;

}
