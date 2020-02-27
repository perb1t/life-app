package com.shijiwei.life.widget.node.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by shijiwei on 2020-02-25.
 *
 * @Desc:
 */
@Target({FIELD })
@Retention(RUNTIME)
public @interface NodeId {
}
