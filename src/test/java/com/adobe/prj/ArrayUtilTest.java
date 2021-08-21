package com.adobe.prj;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.adobe.prj.util.ArrayUtil;

class ArrayUtilTest {

	@Test
	void testGetSum() {
		int expected = 10;
		int[] data = {3,2,5};
		assertEquals(expected, ArrayUtil.getSum(data));
	}

	@Test
	void testGetCount() {
		int expected = 3;
		int[] data = {5,4,1,5,8,5};
		assertEquals(expected, ArrayUtil.getCount(data, 5));
	}

}
