package ru.hh.school.checkupextension.core.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonContainer {
  @JsonProperty("html")
  private String htmlPart;
  @JsonProperty("css")
  private String cssPart;
  @JsonProperty("js")
  private String jsPart;

  public void setHtmlPart(String htmlPart) {
    this.htmlPart = htmlPart;
  }

  public String getHtmlPart() {
    return this.htmlPart;
  }

  public void setCssPart(String cssPart) {
    this.cssPart = cssPart;
  }

  public String getCssPart() {
    return this.cssPart;
  }

  public void setJsPart(String jsPart) {
    this.jsPart = jsPart;
  }

  public String getJsPart() {
    return this.jsPart;
  }

  public static JsonContainer fill(String htmlPart, String cssPart, String jsPart) {
    var container = new JsonContainer();
    container.setHtmlPart(htmlPart);
    container.setCssPart(cssPart);
    container.setJsPart(jsPart);
    return container;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    var that = (JsonContainer) obj;
    return Objects.equals(this.htmlPart, that.htmlPart) &&
        Objects.equals(this.cssPart, that.cssPart) &&
        Objects.equals(this.jsPart, that.jsPart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.htmlPart, this.cssPart, this.jsPart);
  }
}
