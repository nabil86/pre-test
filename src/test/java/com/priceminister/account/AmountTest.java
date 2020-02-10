package com.priceminister.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.priceminister.exception.IllegalAmountValue;

public class AmountTest {

	@Test
	public void testInstanciation() {
		final Amount amount = Amount.ZERO;
		assertTrue(0.0 == amount.getValue());

		final Amount amount2 = new Amount(1.2);
		assertTrue(1.2 == amount2.getValue());

	}

	@Test(expected = IllegalAmountValue.class)
	public void testForbiddenNegativeValue() {
		Amount.valueOf(-1.0);
	}

	@Test
	public void testSumAmount() {
		final Amount am1 = Amount.valueOf(1.0);
		final Amount am2 = Amount.valueOf(1.0);
		final Amount res = am1.add(am2);
		assertEquals(Amount.valueOf(2.0), res);
	}

	@Test
	public void testSubstractAmount() {
		final Amount am1 = Amount.valueOf(1.0);
		final Amount am2 = Amount.valueOf(1.0);
		final Amount res = am1.substract(am2);
		assertEquals(Amount.ZERO, res);
	}

	@Test(expected = IllegalAmountValue.class)
	public void testSubstractThronwExceptionIfNegativeValue() {
		final Amount am1 = Amount.valueOf(1.0);
		final Amount am2 = Amount.valueOf(2.0);
		final Amount res = am1.substract(am2);
		assertEquals(Amount.valueOf(2.0), res);
	}

}
