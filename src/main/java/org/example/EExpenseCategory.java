package org.example;

public enum EExpenseCategory {
    FOOD (1),
    TRANSPORTATION(2),
    UTILITIES(3),
    ENTERTAINMENT(4),
    CLOTHING(5),
    MISCELLANEOUS(6);

    private int categoryOption;

    private EExpenseCategory(int categoryOption) {
        this.categoryOption = categoryOption;
    }

    public int getExpenseCategory() {
        return categoryOption;
    }
}
