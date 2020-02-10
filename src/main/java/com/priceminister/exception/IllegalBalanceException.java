package com.priceminister.exception;

public class IllegalBalanceException extends RuntimeException {

	private static final long serialVersionUID = -9204191749972551939L;

	public IllegalBalanceException(Double illegalBalance) {
		super(String.format("%s: %f", ErrorCode.ILLEGAL_ACCOUNT_BALANCE, illegalBalance));
	}
}
