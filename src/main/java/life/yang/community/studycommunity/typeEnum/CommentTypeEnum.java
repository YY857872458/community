package life.yang.community.studycommunity.typeEnum;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    private final Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : values()) {
            if(value.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
