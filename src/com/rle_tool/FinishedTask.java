package com.rle_tool;

public class FinishedTask {
    private StringBuilder sb;
    private int id;

    public FinishedTask() {
    }

    public FinishedTask(StringBuilder sb, int id) {
        this.sb = sb;
        this.id = id;
    }

    /**
     * 获取
     * @return sb
     */
    public StringBuilder getSb() {
        return sb;
    }

    /**
     * 设置
     * @param sb
     */
    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "FinishedTask{sb = " + sb + ", id = " + id + "}";
    }
}
