package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import structure.TestableFibonacciHeap;

/**
 * 
 * Tests FibonacciHeap
 * @author Stephen Bussey
 *
 */
public class TestFibonacciHeap 
{
	@Test
	public void testConstructor()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		assertEquals(null, fh.min);
		assertEquals(0, fh.n);
	}
	
	@Test
	public void testInsert()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		assertEquals(fh.min, nn3);
		assertEquals(nn3.right, nn);
		assertEquals(nn2.left, nn);
		assertEquals(nn3.left, nn2);
		assertEquals(nn.right, nn2);
		assertEquals(nn2.right, nn3);
		assertEquals(nn.left, nn3);
	}
	
	@Test
	public void testUnion()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		
		TestableFibonacciHeap<String> fh2 = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> n = new TestableFibonacciHeap.Node<String>(3, "D");
		TestableFibonacciHeap.Node<String> n2 = new TestableFibonacciHeap.Node<String>(4, "E");
		TestableFibonacciHeap.Node<String> n3 = new TestableFibonacciHeap.Node<String>(-1, "F");
		fh2.insert(n); fh2.insert(n2); fh2.insert(n3);
		assertTrue(rootListCircular(fh));
		assertTrue(rootListCircular(fh2));
		fh = fh.union(fh, fh2);
		assertTrue(rootListCircular(fh));
		System.out.println(fh.min);
	}
	
	@Test
	public void testExtractMin()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		assertEquals(nn3, fh.extractMin());
		assertTrue(rootListCircular(fh));
		assertEquals(nn, fh.extractMin());
		assertTrue(rootListCircular(fh));
		assertEquals(nn2, fh.extractMin());
		assertTrue(rootListCircular(fh));
	}
	
	@Test
	public void testLargeFH()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		for(int i = 1000; i > 0; i--)
		{
			TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(i, i+"");
			fh.insert(nn);
			assertTrue(rootListCircular(fh));
		}
		while(fh.size() != 0)
		{
			fh.extractMin();
			assertTrue(rootListCircular(fh));
		}
	}
	
	@Test
	public void testBook()
	{
		TestableFibonacciHeap.Node<String> n23 = new TestableFibonacciHeap.Node<String>(23, "23");
		TestableFibonacciHeap.Node<String> n7 = new TestableFibonacciHeap.Node<String>(7, "7");
		TestableFibonacciHeap.Node<String> n21 = new TestableFibonacciHeap.Node<String>(21, "21");
		TestableFibonacciHeap.Node<String> n3 = new TestableFibonacciHeap.Node<String>(3, "3");
		TestableFibonacciHeap.Node<String> n18 = new TestableFibonacciHeap.Node<String>(18, "18");
		TestableFibonacciHeap.Node<String> n39 = new TestableFibonacciHeap.Node<String>(39, "39");
		TestableFibonacciHeap.Node<String> n52 = new TestableFibonacciHeap.Node<String>(52, "52");
		TestableFibonacciHeap.Node<String> n38 = new TestableFibonacciHeap.Node<String>(38, "38");
		TestableFibonacciHeap.Node<String> n41 = new TestableFibonacciHeap.Node<String>(41, "41");
		TestableFibonacciHeap.Node<String> n17 = new TestableFibonacciHeap.Node<String>(17, "17");
		TestableFibonacciHeap.Node<String> n30 = new TestableFibonacciHeap.Node<String>(30, "30");
		TestableFibonacciHeap.Node<String> n24 = new TestableFibonacciHeap.Node<String>(24, "24");
		TestableFibonacciHeap.Node<String> n26 = new TestableFibonacciHeap.Node<String>(26, "26");
		TestableFibonacciHeap.Node<String> n35 = new TestableFibonacciHeap.Node<String>(35, "35");
		TestableFibonacciHeap.Node<String> n46 = new TestableFibonacciHeap.Node<String>(46, "46");
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		
		fh.insert(n23);
		fh.insert(n7);
		fh.insert(n21);
		fh.insert(n3);
		fh.insert(n18);
		fh.insert(n39);
		fh.insert(n52);
		fh.insert(n38);
		fh.insert(n41);
		fh.insert(n17);
		fh.insert(n30);
		fh.insert(n24);
		fh.insert(n26);
		fh.insert(n35);
		fh.insert(n46);
		assertTrue(rootListCircular(fh));
		assertEquals(n3, fh.extractMin());
		assertEquals(n7, fh.extractMin());
		assertEquals(n17, fh.extractMin());
		assertEquals(n18, fh.extractMin());
		assertEquals(n21, fh.extractMin());
		assertEquals(n23, fh.extractMin());
		assertTrue(rootListCircular(fh));
		assertEquals(n24, fh.extractMin());
		assertEquals(n26, fh.extractMin());
		assertEquals(n30, fh.extractMin());
		assertEquals(n35, fh.extractMin());
		assertEquals(n38, fh.extractMin());
		assertEquals(n39, fh.extractMin());
		assertTrue(rootListCircular(fh));
		assertEquals(n41, fh.extractMin());
		assertEquals(n46, fh.extractMin());
		assertEquals(n52, fh.extractMin());
		assertTrue(rootListCircular(fh));
		assertEquals(null, fh.extractMin());
	}
	
	@Test
	public void testDecreaseKey()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		fh.decreaseKey(nn2, -2);
		assertEquals(nn2, fh.min);
		fh.decreaseKey(nn, -3);
		assertEquals(nn, fh.min);
		fh.decreaseKey(nn, 0);
		assertEquals(nn.key, -3, 0);
	}
	
	@Test
	public void testDecreaseKey2()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		nn.p = nn3;
		nn3.degree = 0;
		nn3.child = nn;
		nn.right = nn.left = nn;
		nn2.p = nn;
		nn.child = nn2;
		nn.degree = 1;
		nn2.right = nn2.left = nn2;
		fh.decreaseKey(nn, -1);
		fh.decreaseKey(nn2, -5);
		assertEquals(-1, nn.key, 0);
		assertEquals(-5, nn2.key, 0);
		assertEquals(0, nn3.key, 0);
	}
	@Test
	public void testDelete()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		assertEquals(nn3, fh.min);
		fh.delete(nn3);
		assertEquals(nn, fh.min);
		fh.delete(nn);
		assertEquals(nn2, fh.min);
		fh.delete(nn2);
		assertEquals(null, fh.min);
	}
	
	@Test
	public void testContains()
	{
		TestableFibonacciHeap<String> fh = new TestableFibonacciHeap<String>();
		TestableFibonacciHeap.Node<String> nn = new TestableFibonacciHeap.Node<String>(1, "A");
		TestableFibonacciHeap.Node<String> nn2 = new TestableFibonacciHeap.Node<String>(2, "B");
		TestableFibonacciHeap.Node<String> nn3 = new TestableFibonacciHeap.Node<String>(0, "C");
		fh.insert(nn); fh.insert(nn2); fh.insert(nn3);
		assertTrue(fh.contains(nn));
		fh.delete(nn);
		assertFalse(fh.contains(nn));
	}
	
	
	public static boolean rootListCircular(TestableFibonacciHeap fh)
	{
		TestableFibonacciHeap.Node min = fh.min;
		ArrayList<TestableFibonacciHeap.Node> list = new ArrayList<TestableFibonacciHeap.Node>();
		if(min != null)
		{
			list.add(min);
			min = min.right;
			while(min != fh.min)
			{
				if(list.contains(min))
					return false;
				list.add(min);
				min = min.right;
			}
		}
		min = fh.min;
		list.clear();
		if(min != null)
		{
			list.add(min);
			min = min.left;
			while(min != fh.min)
			{
				if(list.contains(min))
					return false;
				list.add(min);
				min = min.left;
			}
		}
		return true;
	}
}
