package istatistikproje;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file Node.java
 * @assignment Statistics and Probability Term Project
 * @data 13.05.2019
 */
public class Node<T> {
    T data;    
    Node nextNode;

    public Node(T variable) {
        this.data = variable;

        nextNode = null;
    }
}
