package com.rle_tool;


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