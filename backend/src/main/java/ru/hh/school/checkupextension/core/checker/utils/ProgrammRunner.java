package ru.hh.school.checkupextension.core.checker.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProgrammRunner {

  public static Process run(
      String command,
      String workDirectory
  ) throws IOException {
    return new ProcessBuilder(command)
        .directory(new File(workDirectory))
        .start();
  }

  public static Process run(
      String command,
      String arguments,
      String workDirectory
  ) throws IOException {
    var cmd = convertToExecutableFormat(command, arguments);
    return new ProcessBuilder(cmd)
        .directory(new File(workDirectory))
        .start();
  }

  private static List<String> convertToExecutableFormat(String command, String arguments) {
    return Stream.concat(
        Arrays.stream(command.split(" ")),
        Arrays.stream(arguments.split(" "))
    ).toList();
  }
}
