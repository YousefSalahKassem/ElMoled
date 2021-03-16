package com.elmoledmol.www;

import java.util.List;

public class ParentItem {
    private String categoryName;
    private List<ChildItem> childItemList;

    public ParentItem(String categoryName, List<ChildItem> childItemList) {
        this.categoryName = categoryName;
        this.childItemList = childItemList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<ChildItem> getChildItemList() {
        return childItemList;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }
}
