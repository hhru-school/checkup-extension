package ru.hh.school.checkupextension.utils.constant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDtoJsonPropertyName {
  public static final String ID = "id";
  public static final String TYPE_ID = "typeId";
  public static final String TITLE = "title";
  public static final String DESCRIPTION = "description";
  public static final String HTML_PART = "htmlSolution";
  public static final String CSS_PART = "cssSolution";
  public static final String JS_PART = "jsSolution";
  public static final String HTML_TEMPLATE = "htmlTemplate";
  public static final String CSS_TEMPLATE = "cssTemplate";
  public static final String JS_TEMPLATE = "jsTemplate";
  public static final String TEST = "test";
}
