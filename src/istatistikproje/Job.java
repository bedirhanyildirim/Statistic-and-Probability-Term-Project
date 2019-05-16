package istatistikproje;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file Job.java
 * @assignment Statistics and Probability Term Project
 * @data 16.05.2019
 */
public class Job {
    private int arriveTime;
    private int pocessTime;
    private int departureTime;
    private int waitedTime;
    private boolean isDrop;
    
    public Job (int arriveTime) {
        this.arriveTime = arriveTime;
        this.waitedTime = 0;
        this.isDrop = false;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getPocessTime() {
        return pocessTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getWaitedTime() {
        return waitedTime;
    }

    public boolean isIsDrop() {
        return isDrop;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setPocessTime(int pocessTime) {
        this.pocessTime = pocessTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public void setWaitedTime(int waitedTime) {
        this.waitedTime = waitedTime;
    }

    public void setIsDrop(boolean isDrop) {
        this.isDrop = isDrop;
    }
    
}
