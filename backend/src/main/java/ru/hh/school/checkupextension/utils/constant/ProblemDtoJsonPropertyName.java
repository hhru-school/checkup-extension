package ru.hh.school.checkupextension.utils.constant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDtoJsonPropertyName {
  public static final String ID = "id";
  public static final String TYPE = "type";
  public static final String TITLE = "title";
  public static final String CONTENT = "content";
  public static final String MAX_ATTEMPTS = "maxAttempts";
  public static final String ACTIVE = "active";
  public static final String DESCRIPTION = "description";
  public static final String HTML_SOLUTION = "htmlSolution";
  public static final String CSS_SOLUTION = "cssSolution";
  public static final String JS_SOLUTION = "jsSolution";
  public static final String HTML_TEMPLATE = "htmlTemplate";
  public static final String CSS_TEMPLATE = "cssTemplate";
  public static final String JS_TEMPLATE = "jsTemplate";
  public static final String TEST = "test";
}
