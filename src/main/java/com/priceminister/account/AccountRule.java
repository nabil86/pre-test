package com.priceminister.account;

import com.priceminister.account.implementation.Balance;
import com.priceminister.exception.IllegalBalanceException;

/**
 * Checks if the requested operation is permitted.
 */
public interface AccountRule {

	/**
	 * Checks if the resulting account balance after a withdrawal is OK for the
	 * specific type of account.
	 * 
	 * @param balance         is a current balance
	 * @param withdrawnAmount - the money to withdraw
	 * @return true if the operation is permitted, throw an exception otherwise
	 *         {@link IllegalBalanceException}
	 */
	boolean withdrawPermitted(Balance balance, Amount withdrawnAmount);
}
