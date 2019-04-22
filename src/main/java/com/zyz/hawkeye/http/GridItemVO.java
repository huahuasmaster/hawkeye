package com.zyz.hawkeye.http;

import lombok.Data;

@Data
public class GridItemVO {
    // {"w":3,"moved":false,"x":6,"h":6,"y":0,"i":"14"}
    private Integer w;
    private boolean moved;
    private Integer x;
    private Integer h;
    private Integer y;
    private Integer i;
}
