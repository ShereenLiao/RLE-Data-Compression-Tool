package com.test;

import com.rle_tool.SequentialTool;

import java.io.IOException;

public class TestSequantialTool {
    public static void main(String[] args) throws IOException {
        SequentialTool seqTool = new SequentialTool(args);
        seqTool.run();
    }
}
