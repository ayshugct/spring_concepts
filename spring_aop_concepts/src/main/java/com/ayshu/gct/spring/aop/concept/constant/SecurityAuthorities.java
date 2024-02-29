package com.ayshu.gct.spring.aop.concept.constant;

public enum SecurityAuthorities {

	USER("user"),
	ADMIN("admin"),
	MANAGER("manager"),
	HR("hr"),
	EMPLOYEE("employee");
	
	private String value;

	SecurityAuthorities(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
