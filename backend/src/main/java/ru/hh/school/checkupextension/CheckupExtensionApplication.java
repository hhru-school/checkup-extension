package ru.hh.school.checkupextension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hh.school.checkupextension.core.checker.creator.ScreenshotsCreator;
import ru.hh.school.checkupextension.core.data.entity.JsonContainer;
import ru.hh.school.checkupextension.core.data.entity.Problem;

@SpringBootApplication
public class CheckupExtensionApplication {
  public static void main(String[] args) {
    //SpringApplication.run(CheckupExtensionApplication.class, args);
    var htmlFile = Paths.get("/home/sergekorneev/Projects/hh/checkup-extension/backend/utils/Samples/index.html");
    var cssFile = Paths.get("/home/sergekorneev/Projects/hh/checkup-extension/backend/utils/Samples/style.css");
    try {
      var html = Files.readString(htmlFile);
      var css = Files.readString(cssFile);
      JsonContainer container = JsonContainer.fill(html, css, "");

      Problem problem = new Problem();
      problem.setId(0L);
      problem.setReferenceSolution(container);
      ScreenshotsCreator.createScreenshot(problem);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
