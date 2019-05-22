package istatistikproje;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file Sistem.java
 * @assignment Statistics and Probability Term Project
 * @data 16.05.2019
 */
public class Sistem {
    private final int shift;
    private Job [] jobs;
    private final Server [] servers;
    private final ArrayList<Job> queue;
    private final ArrayList<Job> finishedJobs;
    private final ArrayList<Job> dropedJobs;
    public static int time;

    public Sistem() {
        this.shift = 600; //as minutes
        this.servers = new Server[4];
        this.queue = new ArrayList<>();
        this.dropedJobs = new ArrayList<>();
        this.finishedJobs = new ArrayList<>();
        Sistem.time = 0;
        this.createJobs();
        this.createServers();
        this.start();
    }

    public ArrayList<Job> getFinishedJobs() {
        return finishedJobs;
    }

    public ArrayList<Job> getDropedJobs() {
        return dropedJobs;
    }

    public int getShift() {
        return shift;
    }

    public ArrayList<Job> getQueue() {
        return queue;
    }

    public Job[] getJobs() {
        return jobs;
    }

    public Server[] getServers() {
        return servers;
    }
    
    private void createJobs() {
        PoissonProcess pp = new PoissonProcess(300, new Random());
        int num = pp.events();
        int num2 = 0;
        
        Job [] backupJobs = new Job[num];
       
        double sum = 0;
        for (int i = 0; i < num; i++) {
            double a = pp.timeForNextEvent();
            int eventTime = pp.events(sum);
            
            if (eventTime == 0) {
                if (i > 50) {
                    break;
                }
            }
            
            Job j = new Job(eventTime);
            backupJobs[i] = j;
            num2++;
            sum += a;
        }
        
        jobs = new Job[num2];
        
        for (int i = 0; i < backupJobs.length; i++) {
            if (backupJobs[i] != null) {
                jobs[i] = backupJobs[i];
            }
        }
    }
    
    private void createServers() {
        /*
        * There are 3 types of server.
        * These are Gamma, Exponential and Uniform.
        * type = 0 --> Gamma
        * type = 1 --> Gamma
        * type = 2 --> Exponential
        * type = 3 --> Uniform
        */
        servers[0] = new Server(0);
        servers[1] = new Server(1);
        servers[2] = new Server(2);
        servers[3] = new Server(3);
    }
    
    private void start() {
        ArrayList<Server> busyServers = new ArrayList<>();
        ArrayList<Server> notBusyServers = new ArrayList<>();
        
        // every minutes
        for (int i = 0; i < shift; i++) {
            
            // set busyServers and notBusyServers
            busyServers = new ArrayList<>();
            notBusyServers = new ArrayList<>();
            
            // if is there any waiting job in queue, increment them waited time
            if (queue.size() > 0) {
                for (int j = 0; j < queue.size(); j++) {
                    if (queue.get(j).getWaitedTime() == 6) {
                        Job backupJ = queue.remove(j);
                        backupJ.setIsDrop(true);
                        this.dropedJobs.add(backupJ);
                    } else {
                        queue.get(j).incrementWaitedTime();
                    }
                }
            }
            
            // if there is any job right now, take them queue
            for (int j = 0; j < jobs.length; j++) {
                if (jobs[j].getArriveTime() == Sistem.time) {
                    queue.add(jobs[j]);
                }
            }
            
            // find busy and not busy servers
            for (int j = 0; j < servers.length; j++) {
                if (servers[j].isIsBusy() == false) {
                    notBusyServers.add(servers[j]);
                } else {
                    if (servers[j].getReleaseTime() == Sistem.time) {
                        this.finishedJobs.add(servers[j].releaseJob());
                        notBusyServers.add(servers[j]);
                    } else {
                        busyServers.add(servers[j]);
                    }
                }
            }
            
            //find free server and give job to it
            if (notBusyServers.size() > 0 && queue.size() > 0) {
                Random rnd = new Random();
                Server selectedServer = null;
                
                // if there is one free server, give it job
                if (notBusyServers.size() == 1) {
                    // select a server
                    selectedServer = notBusyServers.get(0);
                    // remove from notBusyServers array
                    notBusyServers.remove(0);
                    // give job to server
                    selectedServer.giveJob(queue.get(0));
                    queue.remove(0);
                    // add it to busyServers array
                    busyServers.add(selectedServer);
                    
                    //System.out.println("Busy server count is: "+busyServers.size()+" --- Job count in queue is: "+queue.size()+" --- Time is: " + Sistem.time);
                } else { // if there are more then one servers give them jobs
                    //System.out.println("Busy server count is: "+busyServers.size()+" --- Job count in queue is: "+queue.size()+" --- Time is: " + Sistem.time);
                    
                    // give jobs to servers
                    for (int j = 0; j < notBusyServers.size(); j++) {
                        if (queue.size() > 0) {
                            // select a server randomly
                            int rndNumber = rnd.nextInt(notBusyServers.size());
                            selectedServer = notBusyServers.get(rndNumber);
                            // remove from notBusyServers array
                            notBusyServers.remove(rndNumber);
                            // give the job to selectedServer
                            selectedServer.giveJob(queue.get(0));
                            queue.remove(0);
                            // add to busyServers array
                            busyServers.add(selectedServer);
                        } else {
                            break;
                        }
                    }
                }
            } else { // if all servers are busy
                //System.out.println("Busy server count is: "+busyServers.size()+" --- Job count in queue is: "+queue.size()+" --- Time is: " + Sistem.time);
                
                // check 
                for (int j = 0; j < busyServers.size(); j++) {
                    if (busyServers.get(j).getReleaseTime() <= Sistem.time) {
                        this.finishedJobs.add(busyServers.get(j).releaseJob());
                        busyServers.remove(j);
                    }
                }
            }
            
            // increment busy times
            for (int j = 0; j < busyServers.size(); j++) {
                busyServers.get(j).incrementBusyTime();
            }
            
            //dakikayı bir attır
            Sistem.time++;
        }
        
        // if shift is over and there are some jobs in server
        for (int i = 0; i < busyServers.size(); i++) {
            Job sampleJ = busyServers.get(i).relaseJobAfterShift();
            finishedJobs.add(sampleJ);
            busyServers.remove(i);
        }
    }
}
