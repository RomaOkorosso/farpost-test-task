import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.Scanner;

class Main {

    public Main() {
    }

    public static void main(String[] args) {
        double allowedProcessTime = 0;
        double minUptimePercents = 0;
        boolean fromTerminal = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t")) {
                allowedProcessTime = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equals("-u")) {
                minUptimePercents = Double.parseDouble(args[i + 1]);
            }
        }

        Scanner in = new Scanner(System.in);
        int errCount = 0, responseWithErrs = 0;
        boolean inErr = false;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] newLine = line.split(" ");
            int responseCode = Integer.parseInt(newLine[8]);
            float processTime = Float.parseFloat(newLine[10]);

            if ((responseCode >= 500 && responseCode < 600) || (processTime > allowedProcessTime)) {
                errCount++;
                responseWithErrs++;
                if (!inErr) {
                    System.out.print(newLine[3].substring(1));
                    System.out.print(' ');
                    inErr = true;
                }
            } else {
                if (inErr) {
                    System.out.print(' ');
                    System.out.print(newLine[3].substring(1));
                    System.out.print(' ');
                    System.out.print(errCount);
                    System.out.print('\n');
                    errCount = 0;
                    responseWithErrs = 0;
                    inErr = false;
                }
            }
        }
    }
}
