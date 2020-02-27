package com.shijiwei.life.data.model;

import com.shijiwei.life.widget.node.inject.NodeId;
import com.shijiwei.life.widget.node.inject.NodePid;

/**
 * Created by shijiwei on 2020-02-25.
 *
 * @Desc:
 */
public class Employee {

    private String name;
    @NodeId
    private String id;
    @NodePid
    private String pid;
}
