package cc.sion.common;


public enum StateType {
    START(0,"启用"),
    STOP(1,"停用");

    private int flag;
    private String content;

    StateType(int flag, String content) {
        this.flag = flag;
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public String getContent() {
        return content;
    }
}
