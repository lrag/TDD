package ejemplos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/*
src/test/resources/junit-plattform.properties

junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
*/

public class _09_Test_Paralelos {

	@Test
	public void test1() {
		System.out.println(Thread.currentThread().getName()+": Test 1");
	}
	
	@Test
	public void test2() {
		System.out.println(Thread.currentThread().getName()+": Test 2");
	}
	
	@Test
	public void test3() {
		System.out.println(Thread.currentThread().getName()+": Test 3");
	}
	
	@Test
	public void test4() {
		System.out.println(Thread.currentThread().getName()+": Test 4");
	}
	
	@Test
	public void test5() {
		System.out.println(Thread.currentThread().getName()+": Test 5");
	}
	
	@Test
	public void test6() {
		System.out.println(Thread.currentThread().getName()+": Test 6");
	}
	
	@Test
	public void test7() {
		System.out.println(Thread.currentThread().getName()+": Test 7");
	}
	
	@Test
	public void test8() {
		System.out.println(Thread.currentThread().getName()+": Test 8");
	}

}

