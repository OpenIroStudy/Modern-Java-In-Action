package ch03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessAroundPattern{
    static String path = ProcessAroundPattern.class.getResource("").getPath();
    public static String processFile(BufferedReaderProcessor brp) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path+"data.txt"))){
            return brp.process(br);
        }
    }


    public static void main(String[] args) {
        try {
            System.out.println(processFile(br->br.readLine()+br.readLine()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
