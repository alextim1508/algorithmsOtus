package com.alextim;


import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.out.println("fileIn, fileOut, in/out, simpleRle/improvedRle, replayCountMax");
        }

        FileConverter converter = new FileConverter();

        File fileIn = new File(args[0]);
        File fileOut = new File(args[1]);
        String direct = args[2];

        if(direct.equals("in")) {
            String typeRle = args[3];
            if(typeRle.equals("simpleRle")) {
                converter.simpleRleFileConvert(fileIn, fileOut);
            } else if(typeRle.equals("improvedRle")) {
                byte replayCountMax = Byte.parseByte(args[4]);
                converter.improvedRleFileConvert(fileIn, fileOut, replayCountMax);
            } else
                throw new RuntimeException("Parsing param error!");
        } else if(direct.equals("out")) {
            String typeRle = args[3];
            if(typeRle.equals("simpleRle")) {
                converter.fromSimpleRleFileConvert(fileIn, fileOut);
            } else if(typeRle.equals("improvedRle")) {
                converter.fromImprovedRleFileConvert(fileIn, fileOut);
            } else
                throw new RuntimeException("Parsing param error!");
        } else
            throw new RuntimeException("Parsing param error!");
    }
}
