package structure;

import java.util.Stack;

/**
 * FibonacciHeap data structure - Keeps the minimum and does lazy insertion
 * 
 * @author Stephen Bussey
 *
 * @param <V> Generic type this heap holds
 */
public class TestableFibonacciHeap<V>
{
	/**
	 * FibonacciHeap.Node that contains information needed for the heap
	 * @author Stephen Bussey
	 *
	 * @param <T> The type this Node holds
	 */
	public static class Node<T>
	{
		public Node<T> p, child, left;
		public Node<T> right;
		public int key, okey;
		public T data;
		public int degree;
		public boolean mark, extracted;

		/**
		 * Creates a new node with a given key and data
		 * @param key
		 * @param data
		 */
		public Node(int key, T data)
		{
			this.key = okey = key;
			this.data = data;
			p = child = left = right = null;
			degree = 0;
			mark = extracted = false;
		}
		
		/**
		 * @return this node's key
		 */
		public int getKey()
		{
			return key;
		}

		/**
		 * Set the left node
		 * @param n
		 */
		public void setLeft(Node<T> n) { left = n; }
		
		/**
		 * Set the right node
		 * @param n
		 */
		public void setRight(Node<T> n) { right = n; }
		
		/**
		 * set the child node
		 * @param n
		 */
		public void setChild(Node<T> n) { child = n; }
		
		/**
		 * set the parent node
		 * @param n
		 */
		public void setParent(Node<T> n) { p = n; }
		
		/**
		 * Sets the degree 
		 * @param d
		 */
		public void setDegree(int d) { degree = d; }
		
		/**
		 * marks this node as visited
		 * @param m
		 */
		public void setMark(boolean m) { mark = m; }

		public String toString()
		{
			StringBuffer buf = new StringBuffer();
			buf.append("Key: " + key + "\t");
			buf.append("Data: " + data + "\t");
			if(p!=null)
				buf.append("Parent: " + p.data + "\t");
			else
				buf.append("Parent: null" + "\t");
			if(child != null)
				buf.append("Child: " + child.data + "\t");
			else
				buf.append("Child: null" + "\t");
			if(left != null)
				buf.append("Left: " + left.data + "\t");
			else
				buf.append("Left: null" + "\t");
			if(right != null)
				buf.append("Right: " + right.data + "\t");
			else
				buf.append("Right: null" + "\t");

			return buf.toString();
		}
	}

	public Node<V> min;
	public int n;

	/**
	 * Creates a new empty FibonacciHeap
	 */
	public TestableFibonacciHeap()
	{
		min = null;
		n = 0;
	}

	/**
	 * Inserts a Node<V> into the Heap.  If it is less than the minimum, set the minimum to be this node,
	 * otherwise append it to the root list
	 * @param x
	 */
	public void insert(Node<V> x)
	{
		x.left = x;
		x.right = x;

		if(min != null)
		{
			x.right = min;
			x.left = min.left;
			min.left = x;
			x.left.right = x;
		}
		if(min == null || x.getKey() < min.getKey())
			min = x;
		n++;
	}

	/**
	 * Merges two FibonacciHeaps and gets the new minimum
	 * @param h1 FibonacciHeap
	 * @param h2 FibonacciHeap
	 * @return The merge of these FibonacciHeaps
	 */
	public TestableFibonacciHeap<V> union(TestableFibonacciHeap<V> h1, TestableFibonacciHeap<V> h2)
	{
		TestableFibonacciHeap<V> H = new TestableFibonacciHeap<V>();
		if(h1 != null && h2 != null)
		{
			H.min = h1.min;
			if(H.min != null)
			{
				if(h2.min != null)
				{
					H.min.right.left = h2.min.left;
					h2.min.left.right = H.min.right;
					H.min.right = h2.min;
					h2.min.left = H.min;
					if(h2.min.getKey() < h1.min.getKey())
						H.min = h2.min;
				}
			}
			else
			{
				H.min = h2.min;
			}
			H.n = h1.n + h2.n;
		}
		return H;
	}

	/**
	 * Remove the minimum and restructure
	 * @return The minimum
	 */
	public Node<V> extractMin()
	{
		Node<V> z = min;
		if(z != null)
		{
			Node<V> tempRight;
			Node<V> child = z.child;
			int nKids = z.degree;
			while(nKids > 0)
			{
				tempRight = child.right;

				child.left.right = child.right;
				child.right.left = child.left;

				child.left = min;
				child.right = min.right;
				min.right = child;
				child.right.left = child;

				child.p = null;
				child = tempRight;
				nKids--;
			}
			z.left.right = z.right;
			z.right.left = z.left;
			if(z == z.right)
			{
				min = null;
			}
			else
			{
				min = z.right;
				consolidate();
			}
			n--;
		}
		return z;
	}

	//QUESTION FOR WELLINGTON
	//The max Degree is log n, so why do i need to multiply by oneOverLogPhil to get the correct array length?
	private static final double oneOverLogPhi =
		1.0 / Math.log((1.0 + Math.sqrt(5.0)) / 2.0);

	/**
	 * Restructure this Heap 
	 */
	@SuppressWarnings("unchecked")
	public void consolidate()
	{
		int D = ((int) Math.floor(Math.log(n) * oneOverLogPhi)) + 1;
		Node<V>[] A = new Node[D];
		for(int i = 0; i < D; i++)
			A[i] = null;
		int numRoots = 0;
		Node<V> x  = min;
		if(x != null)
		{
			numRoots++;
			x = x.right;
			while(x != min)
			{
				numRoots++;
				x = x.right;
			}
		}
		while(numRoots > 0)
		{
			int d = x.degree;
			Node<V> next = x.right;
			for(;;)
			{
				Node<V> y = A[d];
				if(y == null)
					break;
				if(x.getKey() > y.getKey())
				{
					Node<V> temp = y;
					y = x;
					x = temp;
				}
				link(y, x);
				A[d] = null;
				d++;
			}
			A[d] = x;
			x = next;
			numRoots--;
		}
		min = null;
		for(int i = 0; i < D; i++)
		{
			if(A[i] == null)
				continue;

			Node<V> child = A[i];
			if(min != null)
			{
				child.left.right = child.right;
				child.right.left = child.left;

				child.left = min;
				child.right = min.right;
				min.right = child;
				child.right.left = child;
				if(child.getKey() < min.getKey())
					min = child;
			}
			else
			{
				min = child;
			}
		}
	}



	private void link(Node<V> y, Node<V> x)
	{
		y.left.right = y.right;
		y.right.left = y.left;

		y.p = x;

		if(x.child == null)
		{
			x.child = y;
			y.right = y;
			y.left = y;
		}
		else
		{
			y.left = x.child;
			y.right = x.child.right;
			x.child.right = y;
			y.right.left = y;
		}
		x.degree ++;

		y.mark = false;
	}

	/**
	 * decrease the key of a node and check if it's a minimum
	 * @param x
	 * @param key
	 */
	public void decreaseKey(Node<V> x, int key)
	{
		if(key > x.getKey())
			return;
		x.key = key;
		Node<V> y = x.p;
		if(y != null && x.getKey() < y.getKey())
		{
			cut(x, y);
			cascadeCut(y);
		}
		if(x.getKey() < min.getKey())
			min = x;
	}

	/**
	 * Cuts the link between x and y
	 * @param x
	 * @param y
	 */
	private void cut(Node<V> x, Node<V> y)
	{
		x.left.right = x.right;
		x.right.left = x.left;
		y.degree--;

		if(y.child == x)
		{
			y.child = x.right;
		}

		if(y.degree == 0)
		{
			y.child = null;
		}

		x.left = min;
		x.right = min.right;
		min.right = x;
		x.right.left = x;

		x.p = null;

		x.mark = false;
	}

	/**
	 * recursive cut method
	 * @param y The node to start at
	 */
	private void cascadeCut(Node<V> y)
	{
		Node<V> z = y.p;
		if(z != null)
		{
			if(!y.mark)
			{
				y.mark = true;
			}
			else
			{
				cut(y, z);
				cascadeCut(z);
			}
		}
	}

	/**
	 * Decrease x's key to -inf and extract it
	 * @param x
	 */
	public void delete(Node<V> x)
	{
		decreaseKey(x, Integer.MIN_VALUE);
		extractMin();
	}

	/**
	 * @return The size of this Heap
	 */
	public int size()
	{
		return n;
	}

	/**
	 * INEFFICIENT AND NOT USED - CHANGE TO USE BOOLEAN VALUES WHEN NEEDED
	 * @param x
	 * @return
	 */
	public boolean contains(Node<V> x)
	{
		if(min == null)
			return false;
		Stack<Node<V>> stack = new Stack<Node<V>>();
		stack.push(min);
		while(!stack.isEmpty())
		{
			Node<V> curr = stack.pop();
			if(curr == x)
				return true;
			if(curr.child!= null)
				stack.push(curr.child);
			Node<V> start = curr;
			curr = curr.right;
			while(curr != start)
			{
				if(curr == x)
					return true;
				if(curr.child!= null)
					stack.push(curr.child);
				curr = curr.right;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return True if this heap is empty
	 */
	public boolean isEmpty()
	{
		return min == null;
	}
}
