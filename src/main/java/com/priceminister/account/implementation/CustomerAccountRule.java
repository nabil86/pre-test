/*
 * =============================================================================
 *
 *   PRICE MINISTER APPLICATION
 *   Copyright (c) 2000 Babelstore.
 *   All Rights Reserved.
 *
 *   $Source$
 *   $Revision$
 *   $Date$
 *   $Author$
 *
 * =============================================================================
 */
package com.priceminister.account.implementation;

import com.priceminister.account.AccountRule;
import com.priceminister.account.Amount;
import com.priceminister.exception.IllegalBalanceException;

public class CustomerAccountRule implements AccountRule {

	@Override
	public boolean withdrawPermitted(Balance balance, Amount withdrawnAmount) {

		final Amount currentAmount = balance.getAmount();
		if (withdrawnAmount.compareTo(currentAmount) > 0) {
			final Double illegalAmount = currentAmount.getValue() - withdrawnAmount.getValue();
			throw new IllegalBalanceException(illegalAmount);
		}

		return true;
	}
}
