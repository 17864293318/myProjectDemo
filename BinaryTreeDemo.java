package com.fzc.servicehi.jacksondemo;



public class BinaryTreeDemo {

    //使用递归遍历二叉树
    //因为根节点要和左右节点关联，所以先创建叶子节点
    public Node init(){
        Node A = new Node(8,null,null);
        Node B = new Node(4,null,null);
        Node C = new Node(2,null,null);
        Node D = new Node(7,null,A);
        Node E = new Node(5,B,null);
        Node F = new Node(1,null,C);
        Node G = new Node(3,F,E);
        Node H = new Node(9,D,null);
        Node J = new Node(6,G,H);
        
        return J;
    
    }
    
    public static void printNode(Node node){
        System.out.println(node.getData());
    }
    
    public static void firstTraversal(Node node){
        printNode(node);
        if (node.getLeftNode() != null){
            firstTraversal(node.getLeftNode());
        }
        if (node.getRightNode() != null){
            firstTraversal(node.getRightNode());
        }
    }
    public static void leftTraversal(Node node){
        if (node.getLeftNode() != null){
            firstTraversal(node.getLeftNode());
        }
        printNode(node);
        if (node.getRightNode() != null){
            firstTraversal(node.getRightNode());
        }
    }
    public static void rightTraversal(Node node){
        if (node.getLeftNode() != null){
            firstTraversal(node.getLeftNode());
        }
        if (node.getRightNode() != null){
            firstTraversal(node.getRightNode());
        }
        printNode(node);
    }
    
    
    
    public static void main(String[] args) {
        BinaryTreeDemo binaryTreeDemo = new BinaryTreeDemo();
        leftTraversal(binaryTreeDemo.init());
    }
    
}
class Node{
    private int data;
    private Node leftNode;
    private Node rightNode;
    
    public int getData() {
        return data;
    }
    
    public void setData(int data) {
        this.data = data;
    }
    
    public Node getLeftNode() {
        return leftNode;
    }
    
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
    
    public Node getRightNode() {
        return rightNode;
    }
    
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
    public Node(){
    }
    
    public Node(int data, Node leftNode, Node rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
    
}
