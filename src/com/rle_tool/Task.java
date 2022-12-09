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

    }
}
