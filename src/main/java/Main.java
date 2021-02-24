import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static String run(String[] args) throws FileNotFoundException {
        int MIN_RESPONSE_CODE = 500;
        int MAX_RESPONSE_CODE = 600;
        double allowedProcessTime = 0;
        double minUptimePercents = 0;
        boolean test = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t")) {
                allowedProcessTime = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equals("-u")) {
                minUptimePercents = Double.parseDouble(args[i + 1]);
            }
            if (args[i].equals("-test")) {
                test = true;

            }
        }
        Scanner in;
        if (!test) {
            in = new Scanner(System.in);
        } else {
            in = new Scanner(new File("access.log"));
        }
        StringBuilder toReturn = new StringBuilder();
        double responseCount = 0;
        double responseWithErrors = 0;
        String entranceLogTime = "";

        while (in.hasNextLine()) {

            String line = in.nextLine();

            String[] newLine = line.split(" ");
            int responseCode = Integer.parseInt(newLine[8]);
            float processTime = Float.parseFloat(newLine[10]);
            responseCount++;

            if (entranceLogTime.isEmpty()) {
                entranceLogTime = newLine[3].substring(1);
            }

            if ((responseCode >= MIN_RESPONSE_CODE && responseCode < MAX_RESPONSE_CODE) ||
                    (processTime > allowedProcessTime)) {

                responseWithErrors++;
                double uptimeWithErrsPercents = 100 - (responseWithErrors / responseCount) * 100;

                if (uptimeWithErrsPercents < minUptimePercents) {
                    if (!test) {
                        System.out.println(entranceLogTime + ' ' + newLine[3].substring(1) + ' ' + uptimeWithErrsPercents);

                    } else {
                        toReturn.append(entranceLogTime).append(' ').
                                append(newLine[3].substring(1)).append(' ').
                                append(uptimeWithErrsPercents).append('\n');
                    }
                    responseCount = 0;
                    responseWithErrors = 0;
                    entranceLogTime = "";
                }
            }
        }
        if (test) {
            return toReturn.toString();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        run(args);
    }
}
