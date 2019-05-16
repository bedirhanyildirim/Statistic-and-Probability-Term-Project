package istatistikproje;
import java.util.Random;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file test.java
 * @assignment Statistics and Probability Term Project
 * @data 13.05.2019
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LinkedListQueue<String> llQueue = new LinkedListQueue<>();
         
        llQueue.printList();
         
        llQueue.dequeue();
         
        llQueue.enqueue("data1");
         
        llQueue.printList();
        
        int pois = new PoissonDistribution(300).sample();
        System.out.println("Poisson 2: " + pois);
        
        double denemee = new ExponentialDistribution(Math.pow(0.3, -1)).sample();
        System.out.println("Exponential: " + denemee);
        
        int denemeee = getUniformRandom(4,9);
        System.out.println("Uniform: " + denemeee);
        
        double sample = new GammaDistribution(7, Math.pow(3, -1)).sample();
        System.out.println("Gamma: " + Math.round(sample));
        
        double samplee = new GammaDistribution(5, Math.pow(2, -1)).sample();
        System.out.println("Gamma: " + Math.round(samplee));
    }
    
    private static int getUniformRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
    
}
