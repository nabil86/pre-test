package com.priceminister.account;

import java.util.Objects;

import com.priceminister.exception.ErrorCode;
import com.priceminister.exception.IllegalAmountValue;

/**
 * immutable class, accept only positive value
 * 
 * @author nabil
 *
 */
public final class Amount implements Comparable<Amount> {

	private final double value;
	public static final Amount ZERO = new Amount(0.0);

	public Amount(Double value) {
		Objects.requireNonNull(value, "value is required");

		if (value < 0) {
			throw new IllegalAmountValue(String.format("%s: %s", ErrorCode.NEGATIVE_AMOUNT_MESSAGE, value));
		}

		this.value = value;
	}

	public static Amount valueOf(Double value) {
		return new Amount(value);
	}

	public double getValue() {
		return value;
	}

	public boolean isNegative() {
		return this.value < 0;
	}

	public Amount add(Amount amount) {
		final double sum = value + amount.value;
		return Amount.valueOf(sum);
	}

	public Amount substract(Amount amount) {
		final double sub = value - amount.value;
		return Amount.valueOf(sub);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
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
		final Amount other = (Amount) obj;
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Amount [value=" + value + "]";
	}

	@Override
	public int compareTo(Amount arg0) {
		if (this.equals(arg0)) {
			return 0;
		}

		return this.value > arg0.value ? 1 : -1;
	}

}
