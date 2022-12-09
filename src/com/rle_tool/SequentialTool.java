package com.rle_tool;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.*;

public class SequentialTool {

    private ArrayList<String> files;

    public SequentialTool(String [] files) {
        this.files = new ArrayList<>();
        for(int i = 0; i < files.length; ++i){
            this.files.add(files[i]);
        }
    }
    private StringBuilder result;
    public void run() throws IOException {
        for(int i = 0; i < files.size(); ++i){
            String data = new String(Files.readAllBytes(Paths.get(files.get(i))));
            rle(data);
        }
        System.out.printf(result.toString());
    }


    public void rle(String data){
        StringBuilder builder = new StringBuilder();
        int count = 0;
        int preChar = Character.MIN_VALUE;//empty char
        for(int i = 0; i < data.length(); ++i){
            if(i == 0 || data.charAt(i) == data.charAt(i - 1)){
                ++count;
            }else{
                builder.append(count).append(preChar);
                count = 0;
                preChar = data.charAt(i);
            }
        }
        if(result.length() >= 2 && builder.length() >= 2 && result.charAt(result.length() - 1) == builder.charAt(1)){
            int new_count = Character.getNumericValue(result.charAt(result.length() - 1)) + Character.getNumericValue(builder.charAt(0));
            result.setCharAt(result.length() - 2, (char) new_count);
            result.append(builder.substring(2));
        }
        else{
            result.append(builder);
        }
    }





}
