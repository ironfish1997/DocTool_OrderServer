package top.liuliyong.orderserver.common.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    LACK_OF_INFORMATION(-10038, "lack of information"), NOT_FOUND(-10039, "target not found");
    private int code;
    private String msg;

    StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
