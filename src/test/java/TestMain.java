import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;

class Processor {
    void processInput(Reader r) {

    }
}

class MyFile {
    public static void createFile(String filename) {
        try {
            File myObj = new File(filename);
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, String textToWrite) {
        /** Write some text into Filename
         * Await int String[] zero index Filename
         * first index text to paste **/
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(textToWrite);
            myWriter.close();

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void delete(String filename) {
        try {
            File f = new File(filename);
            f.delete();
        } catch (Exception err) {
            System.err.println(err.getLocalizedMessage());
        }
    }
}

public class TestMain {


    @Test
    public void testAllBadResponse() throws FileNotFoundException {
        String test = "192.168.32.181 - - [14/06/2015:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 520 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2016:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 144.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 502 2 544.510983 \"-\" \"@list-item-updater\" prio:0";

        String filename = "access.log";
        MyFile.createFile(filename);
        MyFile.writeToFile(filename, test);

        String goodResult = "14/06/2015:16:47:02 14/06/2015:16:47:02 0.0\n" +
                "14/06/2016:16:47:02 14/06/2016:16:47:02 0.0\n" +
                "14/06/2017:16:47:02 14/06/2017:16:47:02 0.0\n";

        String resToCheck = Main.run("-u 95 -t 50 -test".split(" "));
        MyFile.delete(filename);
        assertEquals(goodResult, resToCheck);
    }

    @Test
    public void testAllGoodResponse() throws FileNotFoundException {
        String test = "192.168.32.181 - - [14/06/2015:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2016:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 202 2 44.510983 \"-\" \"@list-item-updater\" prio:0";

        String filename = "access.log";
        MyFile.createFile(filename);
        MyFile.writeToFile(filename, test);

        String goodResult = "";

        String resToCheck = Main.run("-u 95 -t 50 -test".split(" "));
        MyFile.delete(filename);
        assertEquals(goodResult, resToCheck);
    }

    @Test
    public void testNormalInput() throws FileNotFoundException {
        String test = "192.168.32.181 - - [14/06/2010:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2011:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=7ae28555 HTTP/1.1\" 200 2 23.251219 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2012:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=e356713 HTTP/1.1\" 200 2 30.164372 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2013:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6c21c8f6 HTTP/1.1\" 200 2 33.02583 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2014:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=cceed874 HTTP/1.1\" 200 2 35.249855 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2015:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=4b84a53c HTTP/1.1\" 200 2 56.783072 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2016:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=39b3d39 HTTP/1.1\" 200 2 97.679409 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bd456187 HTTP/1.1\" 200 2 43.328125 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2018:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=ea57e6e1 HTTP/1.1\" 200 2 39.73486 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2019:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=15b50208 HTTP/1.1\" 200 2 107.573017 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2020:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=407a047a HTTP/1.1\" 200 2 108.334488 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2020:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=e156f7e HTTP/1.1\" 200 2 69.669118 \"-\" \"@list-item-updater\" prio:0\n";

        String filename = "access.log";
        MyFile.createFile(filename);
        MyFile.writeToFile(filename, test);

        String goodResult = "14/06/2010:16:47:02 14/06/2015:16:47:02 83.33333333333334\n" +
                "14/06/2016:16:47:02 14/06/2016:16:47:02 0.0\n" +
                "14/06/2017:16:47:02 14/06/2019:16:47:02 66.66666666666667\n" +
                "14/06/2020:16:47:02 14/06/2020:16:47:02 0.0\n" +
                "14/06/2020:16:47:02 14/06/2020:16:47:02 0.0\n";

        String resToCheck = Main.run("-u 95 -t 50 -test".split(" "));
        MyFile.delete(filename);
        assertEquals(goodResult, resToCheck);
    }
}
