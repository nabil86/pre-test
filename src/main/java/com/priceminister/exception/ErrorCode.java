package com.priceminister.exception;

public final class ErrorCode {

	private ErrorCode() {
		throw new IllegalStateException("Utility class");
	}

	public static final String NEGATIVE_AMOUNT_MESSAGE = "amount must be positive";
	public static final String ILLEGAL_ACCOUNT_BALANCE = "Illegal account balance";
	public static final String ADDED_NEGATIVE_AMOUNT = "amount must be positive";
	public static final String ADDED_AMOUNT_NUL = "added amount must be not null";
	public static final String WITHDRAW_NEGATIVE = "withdrawn amount must be positive";
	public static final String WITHDRAW_AMOUNT_NUL = "withdrawn amount must be non null";
	public static final String RULE_ACCOUNT_NULL = "A ruleAccount is requirred";

}
