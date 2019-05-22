package istatistikproje;

import java.util.Random;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file Server.java
 * @assignment Statistics and Probability Term Project
 * @data 16.05.2019
 */
public class Server {
    private int type;
    private int busyTime;
    private int servedCount;
    private int releaseTime;
    private boolean isBusy;
    private Job job;

    public Server(int type) {
        this.job = null;
        this.type = type;
        this.busyTime = 0;
        this.isBusy = false;
        this.servedCount = 0;
    }

    public int getBusyTime() {
        return busyTime;
    }

    public void setReleaseTime(int releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getReleaseTime() {
        return releaseTime;
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
    
    public void incrementBusyTime() {
        this.busyTime++;
    }
    
    public void incrementServedCount() {
        this.servedCount++;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
    
    public void giveJob(Job j) {
        this.job = j;
        int duration = 0;
        setIsBusy(true);
        incrementServedCount();
        this.job.setPocessTime(Sistem.time);
        
        if (this.type == 0) {
            duration = server1();
        } else if (this.type == 1) {
            duration = server2();
        } else if (this.type == 2) {
            duration = server3();
        } else if (this.type == 3) {
            duration = server4();
        }
        this.releaseTime = Sistem.time + duration;
    }
    
    public Job releaseJob () {
        // set job's departure time
        this.job.setDepartureTime(Sistem.time);
        // copy that job for return
        Job j = this.job;
        // set server's job null
        this.job = null;
        // set server not busy
        setIsBusy(false);
        setReleaseTime(0);
        
        return j;
    }
    
    public Job relaseJobAfterShift () {
        // set job's departure time
        this.job.setDepartureTime(this.releaseTime);
        // copy that job for return
        Job j = this.job;
        // set server's job null
        this.job = null;
        // set server not busy
        setIsBusy(false);
        setReleaseTime(0);
        return j;
    }
    
    private int server1() {
        double sample = new GammaDistribution(7, Math.pow(3, -1)).sample();
        //System.out.println("Gamma: " + Math.round(sample));
        return (int) Math.round(sample);
    }
    
    private int server2() {
        double sample = new GammaDistribution(5, Math.pow(2, -1)).sample();
        //System.out.println("Gamma: " + Math.round(sample));
        return (int) Math.round(sample);
    }
    
    private int server3() {
        double sample = new ExponentialDistribution(Math.pow(0.3, -1)).sample();
        //System.out.println("Exponential: " + sample);
        return (int) sample;
    }
    
    private int server4() {
        int sample = this.getUniformRandom(4,9);
        //System.out.println("Uniform: " + sample);
        return (int) sample;
    }
    
    private static int getUniformRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
    
}
