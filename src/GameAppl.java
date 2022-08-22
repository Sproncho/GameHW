import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GameAppl {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String num = generateUniqueNumber();

        List<String> history =new ArrayList<>();
        String output = "";
        while (!output.equals("cows:0 bulls:4")){
            String userNum = reader.readLine();
            history.add(userNum);
            output = countAnimals(num,userNum);
            history.add(output);
            System.out.println(output);
        }
        writeHistoryFile(history);
    }
    public static String countAnimals(String actual, String user){
        int cows = 0;
        int bulls = 0;
        int length = user.length() > 4 ? 4 : user.length();
        for (int i = 0; i < length; i++) {
            if(user.charAt(i) == actual.charAt(i))
                bulls++;
            else if(actual.contains(String.valueOf(user.charAt(i))))
                cows++;
        }
        return "cows:" + cows + " bulls:" + bulls;
    }
    public static String generateUniqueNumber(){
        List<String> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            digits.add(String.valueOf(i));

        }
        String res = "";
        for (int i = 0; i < 4; i++) {
            int index = new Random().nextInt(digits.size());
            res += digits.get(index);
            digits.remove(index);
        }
        return res;
    }
    public static void writeHistoryFile(List<String> history) throws FileNotFoundException {
        String output = history.stream().collect(Collectors.joining("\n"));
        String fileName = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm")) + "_"+history.size() + ".txt";
        PrintStream outputStream = new PrintStream(fileName);
        outputStream.println(output);
    }
}
