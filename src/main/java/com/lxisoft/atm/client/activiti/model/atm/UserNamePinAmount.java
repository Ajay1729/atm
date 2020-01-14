package com.lxisoft.atm.client.activiti.model.atm;

public class UserNamePinAmount {
	private String name;
    private Integer pin;
	private Integer amount;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
