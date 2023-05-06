package ru.hh.school.checkupextension.core.data.dto;

import ru.hh.school.checkupextension.core.data.pojo.MyJson;

/**
 * ProblemDto - это класс, который служит для передачи информации о сущности Problem между слоями приложения.
 */
public class ProblemDto {
  private Long id;
  private byte type;
  private String title;
  private String description;
  private String content;
  private boolean active;
  private MyJson template;

  public ProblemDto() {
  }

  public ProblemDto(Long id, byte type, String title, String description, String content,
                    boolean active, MyJson template) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.description = description;
    this.content = content;
    this.active = active;
    this.template = template;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public MyJson getTemplate() {
    return template;
  }

  public void setTemplate(MyJson template) {
    this.template = template;
  }
}
