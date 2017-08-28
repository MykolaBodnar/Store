package ua.editor;

import ua.service.ProducerService;

import java.beans.PropertyEditorSupport;

public class ProducerEditor extends PropertyEditorSupport {
    private ProducerService producerService;

    public ProducerEditor(ProducerService producerService){
        this.producerService = producerService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(producerService.findOne(Integer.parseInt(text)));
    }
}
