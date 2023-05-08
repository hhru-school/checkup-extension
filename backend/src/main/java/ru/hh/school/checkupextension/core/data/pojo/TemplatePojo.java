package ru.hh.school.checkupextension.core.data.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplatePojo implements Serializable {

    @JsonProperty("html")
    private String htmlTemplate;
    @JsonProperty("css")
    private String cssTemplate;
    @JsonProperty("js")
    private String jsTemplate;

    public void setHtmlTemplate(String htmlTemplate) { this.htmlTemplate = htmlTemplate; }
    public String getHtmlTemplate() { return this.htmlTemplate; }

    public void setCssTemplate(String cssTemplate) { this.cssTemplate = cssTemplate; }
    public String getCssTemplate() { return this.cssTemplate; }

    public void setJsTemplate(String jsTemplate) { this.jsTemplate = jsTemplate; }
    public String getJsTemplate() { return this.jsTemplate; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var that = (TemplatePojo) obj;
        return Objects.equals(this.htmlTemplate, that.htmlTemplate) &&
                Objects.equals(this.cssTemplate, that.cssTemplate) &&
                Objects.equals(this.jsTemplate, that.jsTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.htmlTemplate, this.cssTemplate, this.jsTemplate);
    }
}
