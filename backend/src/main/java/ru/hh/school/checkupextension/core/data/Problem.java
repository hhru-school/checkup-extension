package ru.hh.school.checkupextension.core.data;

import ru.hh.school.checkupextension.core.data.pojo.MyJson;

/**
 * table problem:
 *     id - serial primary key,
 *     type - smallint,
 *     title - varchar(255),
 *     description - varchar(255),
 *     content - varchar(255),
 *     active - boolean,
 *     template - jsonb
 */
public interface Problem {
    Long getId();
    void setId(Long id);
    byte getType();
    void setType(byte type);
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    String getContent();
    void setContent(String content);
    boolean getActive();
    void setActive(boolean active);
    MyJson getTemplate();
    void setTemplate(MyJson template);

}
