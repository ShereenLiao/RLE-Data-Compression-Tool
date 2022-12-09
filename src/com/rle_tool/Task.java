package com.rle_tool;

import java.nio.MappedByteBuffer;

public class Task implements Runnable{
    private MappedByteBuffer chunk;
    private long size;
    private int id;
    Task(MappedByteBuffer chunk, long size, int id){
        this.chunk = chunk;
        this.size = size;
        this.id = id;
    }
    @Override
    public void run() {
        rle();
    }

    private StringBuilder rle(){
        int count = 0;
        char currentChar = Character.MIN_VALUE;//empty char
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; ++i){
            if(i == 0 || chunk.get(i) == chunk.get(i - 1)){
                ++count;
            }
            else {
                builder.append(count).append(String.valueOf((char)currentChar));
                count = 1;
            }
            currentChar = (char)chunk.get(i);
        }
        return builder;
    }
}
