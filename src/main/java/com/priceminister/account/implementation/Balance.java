package com.priceminister.account.implementation;

import java.util.Objects;

import com.priceminister.account.Amount;

/**
 * 
 * @author nabil
 *
 */
public class Balance {
	private Amount amount;

	public static Balance of(Amount amount) {
		return new Balance(amount);
	}

	public Balance() {
		this.amount = Amount.ZERO;
	}

	public Balance(Amount amount) {
		super();
		this.amount = amount;
	}

	public Amount getAmount() {
		return amount;
	}

	public void addAmount(Amount value) {
		this.amount = this.amount.add(value);
	}

	public void substract(Amount value) {
		this.amount = this.amount.substract(value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final Balance other = (Balance) obj;
		return Objects.equals(amount, other.amount);
	}

	@Override
	public String toString() {
		return "Balance [" + amount.getValue() + "]";
	}

}
