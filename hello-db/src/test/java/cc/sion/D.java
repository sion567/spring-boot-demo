package cc.sion;

import lombok.Data;

@Data
public class D {
    private int flag;

    public int getFlag() {
        System.out.println("test");
        return -1;
    }
}
