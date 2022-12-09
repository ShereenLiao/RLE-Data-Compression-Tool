package com.rle_tool;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        if(args.length > 1 && args[0].equals("-j")){
            int threadsNum = Integer.valueOf(args[1]);
            //execute the parallel tool
            ParallelTool paraTool = new ParallelTool(args, threadsNum);
            paraTool.run();
        }
        else{
            //execute the sequential tool
            SequentialTool seqTool = new SequentialTool(args);
            seqTool.run();
        }
    }
}
