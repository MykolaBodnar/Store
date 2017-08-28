package ua.editor;

import ua.service.CategoryService;

import java.beans.PropertyEditorSupport;

public class CategoryEditor extends PropertyEditorSupport {
    private CategoryService categoryService;
    public CategoryEditor(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(categoryService.findOne(Integer.parseInt(text)));
    }
}
