package me.jmix.brothertakeaway.form;

import lombok.Data;

@Data
public class CategoryForm {
    // 类目Id
    private Integer categoryId;

    // 类目名称
    private String categoryName;

    // 类目分类
    private Integer categoryType;
}
