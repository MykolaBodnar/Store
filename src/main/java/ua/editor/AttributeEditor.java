package ua.editor;

import ua.service.AttributeService;

import java.beans.PropertyEditorSupport;

public class AttributeEditor extends PropertyEditorSupport {
    private final AttributeService attributeService;

    public AttributeEditor(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(attributeService.findOne(Integer.parseInt(text)));
    }
}
