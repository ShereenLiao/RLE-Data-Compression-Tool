package com.rle_tool;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.*;

public class SequentialTool {
    public class PreviousChar{
        char preChar;
        int preCount;
        PreviousChar(char preChar, int preCount){
            this.preChar = preChar;
            this.preCount = preCount;
        }

        PreviousChar(){
        }

        public char getPreChar() {
            return preChar;
        }

        public void setPreChar(char preChar) {
            this.preChar = preChar;
        }

        public int getPreCount() {
            return preCount;
        }

        public void setPreCount(int preCount) {
            this.preCount = preCount;
        }

        public String toString() {
            return "PreviousChar{preChar = " + preChar + ", preCount = " + preCount + "}";
        }
    }
    private ArrayList<String> files;

    public SequentialTool(String [] files) {
        this.files = new ArrayList<>();
        for(int i = 0; i < files.length; ++i){
            this.files.add(files[i]);
        }
    }

    public void run() throws IOException {
        char lastChar = Character.MIN_VALUE;
        int lastCount = 0;
        for(int i = 0; i < files.size(); ++i){
            String data = new String(Files.readAllBytes(Paths.get(files.get(i))));
            PreviousChar ret = compress(data, lastChar, lastCount);
            lastChar = ret.getPreChar();
            lastCount = ret.getPreCount();
        }
        System.out.printf(lastCount + String.valueOf(lastChar) );
    }

    private PreviousChar compress(String data, char prevChar, int prevCount){
        int count = 0;
        char currentChar = Character.MIN_VALUE;//empty char
        PreviousChar pc = new PreviousChar();
        boolean firstOutput = true;
        for(int i = 0; i < data.length(); ++i){
            if(i == 0 || data.charAt(i) == data.charAt(i - 1)){
                ++count;
            }
            else {
                if(firstOutput){
                    if(prevChar == currentChar){
                        count += prevCount;
                    }
                    else if(prevChar != Character.MIN_VALUE){
                        System.out.println(prevCount+ String.valueOf(prevCount));
                    }
                    firstOutput = false;
                }
                System.out.printf(count + String.valueOf(currentChar));
                count = 1;
            }
            currentChar = data.charAt(i);
        }
        pc.setPreChar(currentChar);
        pc.setPreCount(count);
        return pc;
    }



}
