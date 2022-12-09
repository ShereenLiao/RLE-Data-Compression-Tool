package com.rle_tool;


public class Main {
    public static void main(String[] args) {
        if(args.length > 1 && args[0].equals("-j")){
            int threadsNum = Integer.valueOf(args[1]);
            //execute the parallel tool
            ParallelTool paraTool = new ParallelTool(args, threadsNum);
        }
        else{
            //execute the sequential tool
            SequentialTool seqTool = new SequentialTool(args);

        }
    }
}
