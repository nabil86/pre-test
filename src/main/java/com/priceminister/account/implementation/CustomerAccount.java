package com.priceminister.account.implementation;

import java.util.Objects;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.Amount;
import com.priceminister.exception.ErrorCode;

public class CustomerAccount implements Account {

	private final Balance balance;
	private final AccountRule rule;

	public CustomerAccount(final AccountRule accountRule) {
		Objects.requireNonNull(accountRule, ErrorCode.RULE_ACCOUNT_NULL);

		this.balance = new Balance();
		this.rule = accountRule;
	}

	@Override
	public void add(Amount addedAmount) {
		Objects.requireNonNull(addedAmount, ErrorCode.ADDED_AMOUNT_NUL);
		this.balance.addAmount(addedAmount);
	}

	@Override
	public Amount getBalance() {
		return this.balance.getAmount();
	}

	@Override
	public Amount withdrawAndReportBalance(Amount withdrawnAmount) {
		Objects.requireNonNull(withdrawnAmount, ErrorCode.WITHDRAW_AMOUNT_NUL);

		rule.withdrawPermitted(this.balance, withdrawnAmount);

		this.balance.substract(withdrawnAmount);
		return this.balance.getAmount();
	}
}
