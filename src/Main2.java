import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by beep on 1/21/17.
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long start = System.currentTimeMillis() / 1000;
        //System.out.println("Enter File names to tokenize: ");
        //String filepath1 = br.readLine();
        //String filepath2 = br.readLine();
        WordFrequencies word = new WordFrequencies();
        //Set<String> intersect = word.intersection(filepath1, filepath2);
        if(args.length == 2) {
            Set<String> intersect = word.intersection(args[0], args[1]);
            System.out.print(intersect);
            System.out.println("Count : " + intersect.size());
        } else {
            System.out.println("Wrong input!! Please enter 2 input files");
        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println("Time taken : " + (end-start));
    }
}
