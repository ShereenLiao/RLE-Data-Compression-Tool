package com.rle_tool;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ParallelTool {
    private ArrayList<String> files;
    private ArrayList<String> results;
    private CompletionService<FinishedTask> finishedTasks;
    private ArrayList<Task> chunks;
    private int threadNums;
    static int LENGTH = 4096;
    private ExecutorService pool;
    ParallelTool(String [] files, int threadNums){
        this.files = new ArrayList<>();
        this.threadNums = threadNums;
        this.pool = Executors.newFixedThreadPool(threadNums);
        for(int i = 2; i < files.length; ++i){
            this.files.add(files[i]);
        }
        this.results = new ArrayList<>();
        this.finishedTasks = new ExecutorCompletionService<FinishedTask>(pool);
        this.chunks = new ArrayList<>();
    }

    public void run() throws IOException, ExecutionException, InterruptedException {
        for(String file : files){
            readFiles(file);
        }
        results.ensureCapacity(chunks.size());
        submitTasks();
        collectTasks();
        printResult();
    }


    private void collectTasks() throws ExecutionException, InterruptedException {
        for(int i = 0; i < chunks.size(); ++i){
            results.add(null);
        }
        int received = 0;
        while(received < chunks.size()){
            Future<FinishedTask> future = finishedTasks.take();
            FinishedTask result = future.get();
            results.set(result.getId(), result.getSb().toString());
            received ++;
        }
    }

    private void submitTasks(){
        for(Task t : chunks){
            finishedTasks.submit(t);
        }
    }
    private void readFiles(String file) throws IOException {
        FileChannel fc = new RandomAccessFile(file, "rw").getChannel();
        long sz = fc.size(), start = 0;
        while(start < sz){
            long chunkSize =  Math.min(LENGTH, sz - start);
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, chunkSize);
            //create a task
            Task t = new Task(mbb, chunkSize, chunks.size());
            chunks.add(t);
            start += chunkSize;
        }
    }


    public void printResult() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String result : results) {
            char currChar = result.charAt(1);
            char currCount = result.charAt(0);
            if(result.length() >= 2){
                if(builder.length() >= 2){
                    char lastChar = builder.charAt(result.length() - 1);
                    int lastCount = builder.charAt(result.length() - 2);
                    if(lastChar == currChar){
                        builder.setCharAt(result.length() - 2, (char) ((char)lastCount + currCount));
                        builder.append(result.substring(2));
                    }
                    else{
                        builder.append(result);
                    }
                }
                else{
                    builder.append(result);
                }
            }
        }
        System.out.printf(builder.toString());
        pool.close();
    }
}
