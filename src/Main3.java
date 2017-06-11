/**
 * Created by beep on 1/25/17.
 */
public class Main3 {
    public static void main(String[] args) {
        WordFrequencies wordfreq = new WordFrequencies();
        //System.out.println(args[0]);
        long start = System.currentTimeMillis() / 1000;
        //wordfreq.union("input3.txt", "input4.txt");
        if(args.length == 2) {
            wordfreq.union(args[0], args[1]);
        } else {
            System.out.println("Wrong input!! Please enter 2 input files");
        }
        long end = System.currentTimeMillis() / 1000;
        System.out.print("Time taken : " + (end - start));
    }
}
