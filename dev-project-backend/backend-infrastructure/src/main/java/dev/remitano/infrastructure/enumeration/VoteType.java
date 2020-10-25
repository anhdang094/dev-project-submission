package dev.remitano.infrastructure.enumeration;

public enum VoteType {

    UP(1, "Up"),
    DOWN(2, "Down"),
    UNKNOWN(-1, "Unknown");

    private int code;
    private String message;

    VoteType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static VoteType fromValue(int value) {
        for (VoteType type : VoteType.values()) {
            if (type.getCode() == value) {
                return type;
            }
        }
        return VoteType.UNKNOWN;
    }
}
