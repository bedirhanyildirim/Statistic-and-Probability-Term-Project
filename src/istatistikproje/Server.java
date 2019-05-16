package istatistikproje;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file Server.java
 * @assignment Statistics and Probability Term Project
 * @data 16.05.2019
 */
public class Server {
    private int busyTime;
    private int servedCount;
    private int type;
    private boolean isBusy;

    public Server(int type) {
        this.type = type;
        this.busyTime = 0;
        this.servedCount = 0;
    }

    public int getBusyTime() {
        return busyTime;
    }

    public int getServedCount() {
        return servedCount;
    }

    public int getType() {
        return type;
    }

    public boolean isIsBusy() {
        return isBusy;
    }

    public void setBusyTime(int busyTime) {
        this.busyTime = busyTime;
    }

    public void setServedCount(int servedCount) {
        this.servedCount = servedCount;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
    
}
