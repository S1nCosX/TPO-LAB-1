package main.task2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

import static java.lang.Math.*;

public final class FibonacciHeap<E>
{
    private static final double logPhi = log((1+sqrt(5))/2);

    private final Set<E> elementsIndex = new HashSet<E>();

    private final Comparator<? super E> comparator;

    private int size = 0;
    private int trees = 0;
    private int markedNodes = 0;
    private Node<E> minimumNode;

    public FibonacciHeap()
    {
        this( null );
    }

    public FibonacciHeap( /* @Nullable */Comparator<? super E> comparator )
    {
        this.comparator = comparator;
    }
    private void moveToRoot(Node<E> node)
    {
        if ( isEmpty() )
            minimumNode = node;
        else
        {
            node.getLeft().setRight( node.getRight() );
            node.getRight().setLeft( node.getLeft() );

            node.setLeft(minimumNode);
            node.setRight(minimumNode.getRight());
            minimumNode.setRight( node );
            node.getRight().setLeft( node );

            if (node.compareTo(minimumNode) < 0)
                minimumNode = node;
        }
    }

    public boolean add(E e)
    {
        if (e == null)
            throw new IllegalArgumentException();

        Node<E> node = new Node<E>(e);

        moveToRoot(node);
        size++;

        elementsIndex.add( e );

        return true;
    }

    public void clear()
    {
        minimumNode = null;
        size = 0;
        trees = 0;
        markedNodes = 0;
        elementsIndex.clear();
    }

    public boolean contains(Object o)
    {
        if ( o == null )
            return false;

        return elementsIndex.contains(o);
    }

    public boolean isEmpty()
    {
        return minimumNode == null;
    }

    public int size()
    {
        return size;
    }

    public E peek()
    {
        if ( isEmpty() )
            return null;

        return minimumNode.getData();
    }

    public E poll()
    {
        if ( isEmpty() )
            return null;

        Node<E> z = minimumNode;
        int numOfKids = z.getDegree();

        Node<E> x = z.getChild();
        Node<E> tempRight;

        while (numOfKids > 0)
        {
            tempRight = x.getRight();

            moveToRoot(x);

            x.setParent(null);

            x = tempRight;
            numOfKids--;
        }
        z.getLeft().setRight(z.getRight());
        z.getRight().setLeft(z.getLeft());

        if (z == z.getRight())
            minimumNode = null;
        else
        {
            minimumNode = z.getRight();
            consolidate();
        }

        size--;

        E minimum = z.getData();
        elementsIndex.remove(minimum);
        return minimum;
    }

    public E remove()
    {
        if (isEmpty())
            throw new NoSuchElementException();

        return poll();
    }

    private void consolidate()
    {
        if (isEmpty())
            return;

        int arraySize = ((int)floor(log(size)/logPhi));

        List<Node<E>> nodeSequence = new ArrayList<>( arraySize );
        for (int i = 0; i < arraySize; i++)
            nodeSequence.add( i, null );

        int numRoots = 0;

        Node<E> x = minimumNode;

        if (x != null)
        {
            numRoots++;
            x = x.getRight();

            while (x != minimumNode)
            {
                numRoots++;
                x = x.getRight();
            }
        }

        while (numRoots > 0)
        {
            int degree = x.getDegree();
            Node<E> next = x.getRight();

            while (nodeSequence.get(degree) != null)
            {
                Node<E> y = nodeSequence.get(degree);

                if (x.compareTo(y) > 0)
                {
                    Node<E> pointer = y;
                    y = x;
                    x = pointer;
                }

                link(y, x);

                nodeSequence.set(degree, null);

                degree++;
            }

            nodeSequence.set(degree, x);

            x = next;
            numRoots--;
        }

        minimumNode = null;

        for (Node<E> pointer : nodeSequence)
        {
            if (pointer == null)
                continue;

            if (minimumNode == null)
                minimumNode = pointer;
            else
                moveToRoot(pointer);
        }
    }

    private void link(Node<E> y, Node<E> x)
    {
        y.getLeft().setRight(y.getRight());
        y.getRight().setLeft(y.getLeft());

        y.setParent(x);

        if (x.getChild() == null)
        {
            x.setChild(y);
            y.setRight(y);
            y.setLeft(y);
        }
        else
        {
            y.setLeft(x.getChild());
            y.setRight(x.getChild().getRight());
            x.getChild().setRight(y);
            y.getRight().setLeft(y);
        }

        x.setDegree(x.getDegree()-1);

        y.setMarked(false);
        markedNodes++;
    }

    private void cut(Node<E> x, Node<E> y)
    {
        moveToRoot(x);

        y.setDegree(y.getDegree()-1);
        x.setParent(null);

        x.setMarked(false);
        markedNodes--;
    }
    
    public String toString()
    {
        if ( minimumNode == null )
        {
            return "FibonacciHeap=[]";
        }

        Stack<Node<E>> stack = new Stack<Node<E>>();
        stack.push( minimumNode );

        StringBuilder buf = new StringBuilder( "FibonacciHeap=[" );

        while ( !stack.empty() )
        {
            Node<E> curr = stack.pop();
            buf.append(curr);
            buf.append(", ");

            if (curr.getChild() != null)
            {
                stack.push(curr.getChild());
            }

            Node<E> start = curr;
            curr=curr.getRight();

            while (curr != start)
            {
                buf.append(curr);
                buf.append( ", " );

                if (curr.getChild() != null)
                {
                    stack.push( curr.getChild());
                }

                curr = curr.getRight();
            }
        }

        buf.append( ']' );

        return buf.toString();
    }
}

@Getter
@Setter
@ToString
class Node<E> implements Comparable<Node<E>>{
    E data;

    @ToString.Exclude Node<E> parent;
    @ToString.Exclude Node<E> child;
    @ToString.Exclude Node<E> right;
    @ToString.Exclude Node<E> left;
    @ToString.Exclude int degree;
    @ToString.Exclude Comparator<? super E> comparator;

    @ToString.Exclude boolean marked;

    public Node(E data){
        this.setDegree(0);
        this.setData(data);
        this.setChild(null);
        this.setParent(null);
        this.setLeft(this);
        this.setRight(this);
        this.setMarked(false);
        this.setComparator(null);
    }

    public Node(E data, Comparator<E> comparator){
        this.setDegree(0);
        this.setData(data);
        this.setChild(null);
        this.setParent(null);
        this.setLeft(this);
        this.setRight(this);
        this.setMarked(false);
        this.setComparator(comparator);
    }

    @Override
    public int compareTo(Node<E> o) {
        if (comparator != null)
            return comparator.compare(this.getData(), o.getData());

        @SuppressWarnings( "unchecked" )
        Comparable<? super E> oComparable = (Comparable<? super E>) o.getData();
        return oComparable.compareTo(o.getData());
    }
}