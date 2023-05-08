package ru.hh.school.checkupextension.core.data.nullobject;

import ru.hh.school.checkupextension.core.data.Problem;
import ru.hh.school.checkupextension.core.data.pojo.TemplatePojo;

public class EmptyProblem implements Problem {
    private final static EmptyProblem instance = new EmptyProblem();

    private EmptyProblem(){}

    public static EmptyProblem getInstance() {
        return instance;
    }

    @Override
    public Long getId() {
        return -1L;
    }

    @Override
    public void setId(Long id) { }

    @Override
    public byte getType() {
        return -1;
    }

    @Override
    public void setType(byte type) { }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public boolean getActive() {
        return false;
    }

    @Override
    public void setActive(boolean active) {

    }

    @Override
    public TemplatePojo getTemplate() {
        return null;
    }

    @Override
    public void setTemplate(TemplatePojo template) {

    }

}
