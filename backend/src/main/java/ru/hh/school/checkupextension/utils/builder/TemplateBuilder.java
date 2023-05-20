package ru.hh.school.checkupextension.utils.builder;

import ru.hh.school.checkupextension.core.data.entity.Problem;

public class TemplateBuilder {
  public static Problem.Template buildTemplate(String htmlPart, String cssPart, String jsPart) {
    var template = new Problem.Template();
    template.setHtmlTemplate(htmlPart);
    template.setCssTemplate(cssPart);
    template.setJsTemplate(jsPart);
    return template;
  }
}
