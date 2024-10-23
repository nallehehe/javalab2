package org.example;

public enum EExpenseCategory {
    FOOD (1),
    TRANSPORTATION(2),
    UTILITIES(3),
    ENTERTAINMENT(4),
    CLOTHING(5),
    MISCELLANEOUS(6);

    //https://www.geeksforgeeks.org/enum-customized-value-java/
    private int expenseCategoryOption;

    private EExpenseCategory(int expenseCategoryOption) {
        this.expenseCategoryOption = expenseCategoryOption;
    }

    public int getExpenseCategory() {
        return expenseCategoryOption;
    }
}
