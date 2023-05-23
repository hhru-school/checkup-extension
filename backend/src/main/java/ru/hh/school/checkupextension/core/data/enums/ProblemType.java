package ru.hh.school.checkupextension.core.data.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ProblemType {
  JS((byte) 0),
  HTML((byte) 1),
  UNKNOWN((byte) 2);

  private static final Map<Byte, String> types;
  private static final Map<String, Byte> names;

  static {
    var jsName = "js";
    var htmlName = "html";
    var unknown = "unknown";
    types = new HashMap<>();
    types.put(JS.typeCode, jsName);
    types.put(HTML.typeCode, htmlName);
    types.put(UNKNOWN.typeCode, unknown);

    names = new HashMap<>();
    names.put(jsName, JS.typeCode);
    names.put(htmlName, HTML.typeCode);
    names.put(unknown, UNKNOWN.typeCode);
  }

  public static String getTitleBy(byte code) {
    return Optional.ofNullable(types.get(code))
        .orElse("unknown");
  }

  public static byte getCodeBy(String title) {
    return Optional.ofNullable(names.get(title))
        .orElse(UNKNOWN.typeCode);
  }

  private final byte typeCode;

  ProblemType(byte code) {
    this.typeCode = code;
  }

  public byte getCode() {
    return this.typeCode;
  }

  public String getTitle() {
    return this.toString();
  }

  @Override
  public String toString() {
    return types.get(this.typeCode);
  }
}
