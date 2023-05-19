package ru.hh.school.checkupextension.core.data.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ProblemType {
    CHECKED((byte) 0),
    ACCEPTED((byte) 1),
    REFUSED((byte) 2);

    private static final Map<Byte, String> types;

    static {
        types = new HashMap<>();
        types.put((byte) 0, "checked");
        types.put((byte) 1, "success");
        types.put((byte) 2, "fault");
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
