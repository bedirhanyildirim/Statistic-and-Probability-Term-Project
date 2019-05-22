/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istatistikproje;

import java.util.ArrayList;

/**
 *
 * @author bedirhan
 */
public class MonteCarlo {
    
    private final int count;
    private int dropedJobCount;
    private int notWaitedJobCount;
    private int avarageWaitingTime;
    private int longestWaitingTime;
    private int remainingJobInSystem;
    private ArrayList<Sistem> sistemList;
    private int[] totalNumberOfJobsEachServer;
    private int[] totalTimeEachServerIsBusyWithJobs;
    
    public MonteCarlo (int count) {
        this.count = count;
        this.dropedJobCount = 0;
        this.notWaitedJobCount = 0;
        this.avarageWaitingTime = 0;
        this.longestWaitingTime = 0;
        this.remainingJobInSystem = 0;
        this.sistemList = new ArrayList<>();
        this.totalTimeEachServerIsBusyWithJobs = new int[4];
        this.totalNumberOfJobsEachServer = new int[4];
    }
    
    public void start () {
        for (int i = 0; i < this.count; i++) {
            Sistem s = new Sistem();
            this.sistemList.add(s);
        }
        reportResult();
    }
    
    private void reportResult () {
        System.out.println(this.sistemList.size() + " sistem worked.");
        //the total time each server is busy with jobs
        this.totalTimeEachServerBusy();
        this.findAvarageWaitingTime();
        this.findJobDepartureByTime(603);
    }
    
    private void totalTimeEachServerBusy () {
        // fill with 0 array
        for (int i = 0; i < this.totalTimeEachServerIsBusyWithJobs.length; i++) {
            this.totalNumberOfJobsEachServer[i] = 0;
            this.totalTimeEachServerIsBusyWithJobs[i] = 0;
        }
        
        // check every sistem and every server
        for (int i = 0; i < this.sistemList.size(); i++) {
            for (int j = 0; j < this.sistemList.get(i).getServers().length; j++) {
                this.totalTimeEachServerIsBusyWithJobs[j] += this.sistemList.get(i).getServers()[j].getBusyTime();
                this.totalNumberOfJobsEachServer[j] += this.sistemList.get(i).getServers()[j].getServedCount();
            }
        }
        
        // print it
        System.out.println("the total time each server is busy with jobs:");
        for (int i = 0; i < this.totalTimeEachServerIsBusyWithJobs.length; i++) {
            System.out.println((i+1) + ". server: " + this.totalTimeEachServerIsBusyWithJobs[i]);
        }
        System.out.println("the total number of jobs served by each server:");
        for (int i = 0; i < this.totalNumberOfJobsEachServer.length; i++) {
            System.out.println((i+1) + ". server: " + this.totalNumberOfJobsEachServer[i]);
        }
    }
    
    private void findAvarageWaitingTime () {
        int sum = 0;
        int count = 0;
        int longest = 0;
        int droped = 0;
        int notWaited = 0;
        for (int i = 0; i < this.sistemList.size(); i++) {
            for (int j = 0; j < this.sistemList.get(i).getFinishedJobs().size(); j++) {
                
                // find average
                sum += this.sistemList.get(i).getFinishedJobs().get(j).getWaitedTime();
                count++;
                
                // find max
                if (j == 0) {
                    longest = this.sistemList.get(i).getFinishedJobs().get(j).getWaitedTime();
                } else {
                    if (longest < this.sistemList.get(i).getFinishedJobs().get(j).getWaitedTime()) {
                        longest = this.sistemList.get(i).getFinishedJobs().get(j).getWaitedTime();
                    } 
                }
                
                // find not waited
                if (this.sistemList.get(i).getFinishedJobs().get(j).getWaitedTime() == 0) {
                    notWaited++;
                }
            }
            droped += this.sistemList.get(i).getDropedJobs().size();
        }
        this.dropedJobCount = droped;
        this.longestWaitingTime = longest;
        this.avarageWaitingTime = sum/count;
        this.notWaitedJobCount = notWaited;
        
        // print it
        System.out.println("the average waiting time: " + this.avarageWaitingTime);
        System.out.println("the longest waiting time: " + this.longestWaitingTime);
        System.out.println("the number of withdrawn jobs: " + this.dropedJobCount);
        System.out.println("the number of jobs with no waiting time: " + this.notWaitedJobCount);
    }
    
    private void findJobDepartureByTime (int time) {
        int countJob = 0;
        for (int i = 0; i < this.sistemList.size(); i++) {
            for (int j = 0; j < this.sistemList.get(i).getFinishedJobs().size(); j++) {
                if (this.sistemList.get(i).getFinishedJobs().get(j).getDepartureTime() == time) {
                    countJob++;
                }
            }
        }
        this.remainingJobInSystem = countJob;
        
        // print it
        System.out.println("the number fo jobs remanining in the system at 6:03pm: " + this.remainingJobInSystem);
    }
    
}
