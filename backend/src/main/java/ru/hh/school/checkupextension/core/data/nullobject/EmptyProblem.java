package ru.hh.school.checkupextension.core.data.nullobject;

import ru.hh.school.checkupextension.core.data.Problem;

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
    public String getCondition() {
        return "";
    }

    @Override
    public void setCondition(String condition) { }

    @Override
    public byte getType() {
        return -1;
    }

    @Override
    public void setType(byte type) { }

    @Override
    public Short getMaxAttempts() {
        return 0;
    }

    @Override
    public void setMaxAttempts(Short maxAttempts) { }
}
