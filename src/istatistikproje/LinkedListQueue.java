package istatistikproje;

/**
 *
 * @author Bedirhan Yıldırım | bedirhan.yildirim@stu.fsm.edu.tr
 * @description 
 * @file LinkedListQueue.java
 * @assignment Statistics and Probability Term Project
 * @data 13.05.2019
 */
public class LinkedListQueue<T> {
    private Node<T> header;
    private int size = 0;
 
    public int getSize() {
        return size;
    }
     
    void enqueue(T data){   //addLast
        Node<T> newNode = new Node(data);
        if(header == null){
            header = newNode;
        }
        else{
            Node<T> temp = header;
            while(temp.nextNode != null){
                temp = temp.nextNode;
            }
            temp.nextNode = newNode;
        }
        size++;
    }
     
    T dequeue(){        //removeFirst
        if(header != null){
            Node<T> temp = header;
            header = header.nextNode;
            temp.nextNode = null;
            size--;
            return temp.data;
        }
        else{
            System.out.println("Liste boş !");
            return null;
        }        
    }
     
    void printList(){
        if(header == null){
            System.out.println("Liste boş");
        }
        else{
            Node<T> temp = header;
            while(temp != null){
                System.out.print(temp.data + " ");
                temp = temp.nextNode;
            }
            System.out.println("");
        }
    }
}