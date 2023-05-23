package ru.hh.school.checkupextension.core.data.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SubmissionsStatus {
  CHECKED((byte) 0),
  ACCEPTED((byte) 1),
  REFUSED((byte) 2);

  private static final Map<Byte, String> statuses;

  static {
    statuses = new HashMap<>();
    statuses.put(CHECKED.statusCode, "checked");
    statuses.put(ACCEPTED.statusCode, "success");
    statuses.put(REFUSED.statusCode, "fault");
  }

  public static String getTitleBy(byte code) {
    return Optional.ofNullable(statuses.get(code))
        .orElse("unknown");
  }

  private final byte statusCode;

  SubmissionsStatus(byte code) {
    this.statusCode = code;
  }

  public byte getCode() {
    return this.statusCode;
  }

  public String getTitle() {
    return this.toString();
  }

  @Override
  public String toString() {
    return statuses.get(this.statusCode);
  }
}
