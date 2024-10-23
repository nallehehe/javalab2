package org.example;

public enum EIncomeCategory {
    SALARY(1),
    GIFTS(2),
    RETURNS(3);

    private int incomeCategoryOption;

    private EIncomeCategory(int incomeCategoryOption) {
        this.incomeCategoryOption = incomeCategoryOption;
    }

    public int getIncomeCategoryOption() {
        return incomeCategoryOption;
    }
}
