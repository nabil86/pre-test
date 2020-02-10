package com.priceminister.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import com.priceminister.exception.ErrorCode;
import com.priceminister.exception.IllegalAmountValue;
import com.priceminister.exception.IllegalBalanceException;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {

	// given
	private static final Amount TEN = Amount.valueOf(10.0);

	private Account customerAccount;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		final CustomerAccountRule accountRule = new CustomerAccountRule();
		customerAccount = new CustomerAccount(accountRule);
	}

	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		// given
		final Amount zero = Amount.ZERO;
		// when
		final Amount initialBalance = customerAccount.getBalance();
		// then
		assertEquals("Initial balance isn't equal Zero", zero, initialBalance);
	}

	@Test
	public void testAddPositiveAmount() {
		// when
		customerAccount.add(TEN);

		// then
		final Amount balanceResult = customerAccount.getBalance();
		assertEquals("Amount TEN isn't added to the balance", TEN, balanceResult);
	}

	@Test
	public void testMultipleAddPositiveAmount() {
		// given
		final Amount amountToAdd = Amount.valueOf(1.111);

		customerAccount.add(amountToAdd);
		customerAccount.add(amountToAdd);
		customerAccount.add(amountToAdd);

		final Amount expectedBalance = Amount.valueOf(3.333);

		// when
		final Amount balanceAmount = customerAccount.getBalance();

		// then
		assertEquals("Operation add multiple values is failed", expectedBalance, balanceAmount);
	}

	@Test
	public void testWithdrawAndReportBalanceIllegalBalance() {
		// given
		final Amount withdraw = TEN;

		// then
		final double expectedBalance = -10.0;
		final String expectedMessage = String.format("%s: %f", ErrorCode.ILLEGAL_ACCOUNT_BALANCE, expectedBalance);
		thrown.expect(IllegalBalanceException.class);
		thrown.expectMessage(expectedMessage);

		// when
		customerAccount.withdrawAndReportBalance(withdraw);
	}

	@Test
	public void testWithdrawAndReportBalanceIllegalWithdrawAmount() {

		final String expectedMessage = String.format("%s: %s", ErrorCode.NEGATIVE_AMOUNT_MESSAGE, "-1.0");
		thrown.expect(IllegalAmountValue.class);
		thrown.expectMessage(expectedMessage);

		final Amount negativeAmount = Amount.valueOf(-1.0);
		customerAccount.withdrawAndReportBalance(negativeAmount);
	}

	@Test
	public void testWithdrawAndReportBalance() {
		// given
		final Amount initialBalance = Amount.valueOf(100.);
		customerAccount.add(initialBalance);

		final Amount withdraw = Amount.valueOf(50.15);
		// when
		customerAccount.withdrawAndReportBalance(withdraw);

		// then
		assertEquals("withdraw operation failed", Amount.valueOf(49.85), customerAccount.getBalance());
	}

	@Test
	public void testWithdrawAndBalanceAreEquals() {
		// given
		final Amount initialBalance = TEN;
		customerAccount.add(initialBalance);
		// when
		customerAccount.withdrawAndReportBalance(TEN);

		// Then
		assertEquals("withdraw operation failed", Amount.ZERO, customerAccount.getBalance());

	}
}