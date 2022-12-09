package com.rle_tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelTool {
    private ArrayList<String> files;
    private int threadNums;
    static int length = 4096;
    private ExecutorService pool;
    ParallelTool(String [] files, int threadNums){
        this.files = new ArrayList<>();
        this.threadNums = threadNums;
        this.pool = Executors.newFixedThreadPool(3);
        for(int i = 2; i < files.length; ++i){
            this.files.add(files[i]);
;        }
    }

    public void run(){

    }

    public int readFiles(String file, int prevId) throws IOException {
        FileChannel fc = new RandomAccessFile("file", "rw").getChannel();
        long sz = fc.size(), start = 0;
        while(start < sz){
            long chunkSize =  Math.max(length, start - sz + 1);
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, chunkSize);
            //create a task and submit it to the thread pool
            Task t = new Task(mbb, chunkSize, prevId);
            pool.submit(t);
            start += length;
            prevId ++;
        }
        return prevId;
    }
}
