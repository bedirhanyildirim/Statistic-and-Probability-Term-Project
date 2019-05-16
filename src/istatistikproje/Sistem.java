package istatistikproje;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file Sistem.java
 * @assignment Statistics and Probability Term Project
 * @data 16.05.2019
 */
public class Sistem {
    private Job [] jobs;
    private Server [] servers;
    private LinkedListQueue<Job> queue;
    public static int time;

    public Sistem() {
        this.servers = new Server[4];
        this.queue = new LinkedListQueue<>();
        Sistem.time = 0;
    }

    public Job[] getJobs() {
        return jobs;
    }

    public Server[] getServers() {
        return servers;
    }

    public LinkedListQueue<Job> getQueue() {
        return queue;
    }

    public static int getTime() {
        return time;
    }

    public void setJobs(Job[] jobs) {
        this.jobs = jobs;
    }

    public void setServers(Server[] servers) {
        this.servers = servers;
    }

    public void setQueue(LinkedListQueue<Job> queue) {
        this.queue = queue;
    }

    public static void setTime(int time) {
        Sistem.time = time;
    }
    
}
