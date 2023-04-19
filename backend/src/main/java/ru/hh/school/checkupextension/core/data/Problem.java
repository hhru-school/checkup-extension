package ru.hh.school.checkupextension.core.data;

public interface Problem {
    Long getId();
    void setId(Long id);

    String getCondition();
    void setCondition(String condition);

    byte getType();
    void setType(byte type);

    Short getMaxAttempts();
    void setMaxAttempts(Short maxAttempts);
}
