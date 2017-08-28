package ua.editor;

import ua.service.AttributeValueService;

import java.beans.PropertyEditorSupport;

public class AttributeValueEditor extends PropertyEditorSupport {

    private AttributeValueService attributeValueService;

    public AttributeValueEditor(AttributeValueService attributeValueService){
        this.attributeValueService = attributeValueService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(attributeValueService.findOneWithAttribute(Integer.parseInt(text)));
    }
}
