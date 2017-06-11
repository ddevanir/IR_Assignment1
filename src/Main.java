import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Created by beep on 1/21/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long start = System.currentTimeMillis() / 1000;
        //System.out.println("Enter File name to tokenize: ");
        //String filepath = br.readLine();
        WordFrequencies word = new WordFrequencies();
        //List<String> tokenize = word.tokenize(filepath);
        if(args.length == 1) {
            Map<String, Integer> tokenize = word.tokenize(args[0]);
            //Map<String, Integer> freqCount = word.computeWordFrequencies(tokenize);
            //word.print(freqCount);
            word.print(tokenize);
        } else {
            System.out.println("Wrong input!! Please enter 1 input file");
        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println("Time taken : " + (end-start));
    }
}
