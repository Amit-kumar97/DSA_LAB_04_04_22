import java.util.ArrayList;
import java.util.List;

/*
Create Node class structure of Tree,also create a class name BST where you will write insert method using recursion
along with that some more method is there,
1.Find minimum in the Right SubTree
2.Maximum in the Left SubTree
3.Find how many values are in between 10 - 15 (Inclusive Range)
4.Searching Operation
5.Traversal Techniques
            |-inOrder Traversal
            |-preOrder Traversal
            |-postOrder Traversal
6.to check the given tree is BST or not

The tree looks like this:
          10
       /      \
     5         15
   /  \        /   \
 4     6      12    30

 */

public class Tree01 {

    //----Node Class Structure
    class Node
    {
        int data;
        Node left, right;
        //Constructor
        public Node(int data)
        {
            this.data = data;
            left = right = null;
        }
    }

    //--------Insert Method
    public Node insert(Node root,int data)
    {
        Node newnode = new Node(data);

        //---when nothing in the tree
        if (root == null)
        {
            root = newnode;
            return root;
        }

        if (root.data > newnode.data)
        {
            root.left = insert(root.left, data);
        }
        else
        {
            root.right = insert(root.right, data);
        }

        return root;
    }

    //------Traversal Techniques-Inorder Traversal[LDR]
    public void inOrderTraversal(Node root)
    {
        //Base condition
        if(root==null)
            return;

        //--------[LDR]
        inOrderTraversal(root.left);
        System.out.print(root.data+" ");
        inOrderTraversal(root.right);
    }

    //------Traversal Techniques-preOrder Traversal[LDR]
    public void preOrderTraversal(Node root)
    {
        //Base Condition
        if(root==null)
            return;

        //---------[DLR]
        System.out.print(root.data+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //------Traversal Techniques-postOrder Traversal[LDR]
    public void postOrderTraversal(Node root)
    {
        //Base condition
        if(root==null)
            return;

        //-------[LRD]
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data+" ");
    }

    //-------for the search operation in the tree
    public boolean search(Node root,int values)
    {
        boolean res;
        Node newnode = new Node(values);

        if(root==null)
            return false;

        if(root.data== newnode.data)
            return true;

        if(root.data> newnode.data)
        {
            res = search(root.left,values);
        }
        else
        {
            res = search(root.right,values);
        }
        return res;
    }

    public int minvalues(Node root)
    {
        Node temp = root;
        while(temp!=null)
        {
            temp = temp.right;
        }
        return temp.data;
    }

    public int maxvalues(Node root)
    {
        int max = root.data;
        while(root.left!=null)
        {
            max = root.left.data;
            root = root.left;
        }
        return max;
    }

    //---------Creating a list to hold the values in the list so that we can check what are the values in the range
    //---------from 10-15 and also we can count number of values in the given range by using list.size() method
    List<Integer> list = new ArrayList<>();
    public void range(Node root)
    {
        int count = 0;
        Node temp = root;
        if(temp!=null)
        {
            if(temp.data>=10 && temp.data<=15)
            {
                System.out.print(temp.data+" ");
                list.add(temp.data);
            }
            range(temp.left);
            range(temp.right);
        }

    }

    //-------------Method to check the given tree is BST or Not
    public boolean checkBST(Node root,int min,int max)
    {
        //Base condition
        if(root==null)
            return true;

        //---------The left subtree have nodes having a value less than the root
        if(root.data<min)
            return false;

        //---------The right subtree should contain the nodes having data greater than root
        if(root.data>max)
            return false;

        //-------------if the root satisfies the value then we recursively check the same for left and right subtree
        //-------------we set the minimum and maximum for left and right subtrees accordingly
        return checkBST(root.right, root.data, max) && checkBST(root.left,min,root.data);

    }

    //---------Main method
    public static void main(String[] args) {

        Tree01 obj = new Tree01();

        Node root = obj.insert(null,10);
        Node res = obj.insert(root,5);
        res = obj.insert(root,4);
        res = obj.insert(root,6);
        res = obj.insert(root,15);
        res = obj.insert(root,12);
        res = obj.insert(root,30);

        System.out.println("In order-");
        obj.inOrderTraversal(root);
        System.out.println();

        System.out.println("pre-order");
        obj.preOrderTraversal(root);
        System.out.println();

        System.out.println("Post-order-");
        obj.postOrderTraversal(root);
        System.out.println();

//        System.out.println(obj.minvalues(root));
//        System.out.println(obj.maxvalues(root));

        System.out.println("Element in the Range 10-15:");
        obj.range(root);
        System.out.println();
        System.out.println("Count total number of Range:");
        System.out.println(obj.list.size());

        System.out.println("The given Element is Exist or not: "+obj.search(root,15));

        System.out.println("The given Tree is BST or Not: "+obj.checkBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
    }
}

