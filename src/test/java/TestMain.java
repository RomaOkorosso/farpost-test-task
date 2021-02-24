import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.*;


public class TestMain {
    private void createFileAndWriteTest(String test) throws IOException {
        String filename = "access.log";
        File testFile = new File(filename);
        boolean check = testFile.createNewFile();
        //System.out.println("CHECK CREATE FILE= " + check);
//        if (!check) {
//            fail("cant create file");
//        }
        FileWriter writer = new FileWriter(filename);
        writer.write(test);
        writer.close();
    }


    private void deleteTestFile() {
        File testFile = new File("access.log");
        boolean check = testFile.delete();
        //System.out.println("CHECK WRITE TO FILE= " + check);
//        if (!check) {
//            fail("cant delete file");
//        }
    }


    @Test
    public void testAllBadResponse() throws IOException {
        String test = "192.168.32.181 - - [14/06/2015:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 520 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2016:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 144.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 502 2 544.510983 \"-\" \"@list-item-updater\" prio:0";

        createFileAndWriteTest(test);
        String goodResult = "14/06/2015:16:47:02 14/06/2015:16:47:02 0.0\n" +
                "14/06/2016:16:47:02 14/06/2016:16:47:02 0.0\n" +
                "14/06/2017:16:47:02 14/06/2017:16:47:02 0.0\n";

        String resToCheck = Main.run("-u 95 -t 50 -test".split(" "));
        deleteTestFile();
        assertEquals(goodResult, resToCheck);
    }


    @Test
    public void testAllGoodResponse() throws IOException {
        String test = "192.168.32.181 - - [14/06/2015:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2016:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n" +
                "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 202 2 44.510983 \"-\" \"@list-item-updater\" prio:0";

        createFileAndWriteTest(test);
        String goodResult = "";

        String resToCheck = Main.run("-u 95 -t 50 -test".split(" "));
        deleteTestFile();
        assertEquals(goodResult, resToCheck);
    }

    @Test
    public void testNormalInput() throws IOException {
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

        createFileAndWriteTest(test);

        String goodResult = "14/06/2010:16:47:02 14/06/2015:16:47:02 83.33333333333334\n" +
                "14/06/2016:16:47:02 14/06/2016:16:47:02 0.0\n" +
                "14/06/2017:16:47:02 14/06/2019:16:47:02 66.66666666666667\n" +
                "14/06/2020:16:47:02 14/06/2020:16:47:02 0.0\n" +
                "14/06/2020:16:47:02 14/06/2020:16:47:02 0.0\n";

        String resToCheck = Main.run("-u 95 -t 50 -test".split(" "));
        deleteTestFile();
        assertEquals(goodResult, resToCheck);
    }
}
