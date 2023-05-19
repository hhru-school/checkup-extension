package ru.hh.school.checkupextension.core.data.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ProblemType {
    JS((byte) 0),
    HTML((byte) 1);

    private static final Map<Byte, String> types;

    static {
        types = new HashMap<>();
        types.put((byte) 0, "JS");
        types.put((byte) 1, "HTML");
    }

    public static String getTitleBy(byte code) {
        return Optional.ofNullable(types.get(code))
                .orElse("unknown");
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
