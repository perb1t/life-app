package com.shijiwei.life.widget.node;

import com.shijiwei.life.widget.node.inject.NodeId;
import com.shijiwei.life.widget.node.inject.NodePid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Created by shijiwei on 2020-02-25. @Desc: */
public class NodeManager {

  /**
   * 将对象转化Node节点
   *
   * @param datas
   * @param <E>
   * @return
   * @throws IllegalAccessException
   */
  public static <E> List<Node<E>> convert(List<E> datas) {
    List<Node<E>> nodes = new ArrayList<>();
    if (datas != null) {
      for (E e : datas) {
        if (e != null) {
          nodes.add(new Node<>(e));
        }
      }
      if (datas.size() > 1) {
        /**/
        for (int i = 0; i < nodes.size() - 1; i++) {
          E a = nodes.get(i).getData();
          String aId = getField(a, NodeId.class);
          String aPid = getField(a, NodePid.class);
          for (int j = i + 1; j < nodes.size(); j++) {
            E b = nodes.get(j).getData();
            String bId = getField(b, NodeId.class);
            String bPid = getField(b, NodePid.class);
          }
        }
      }

      List<Node<E>> result = new ArrayList<>();
      for (Node node : nodes) {
        if (node.getParent() == null) {
          result.add(node);
        }
      }
      return result;
    }

    return null;
  }

  /**
   * 获取对象属性值
   *
   * @param e
   * @param clz
   * @param <E>
   * @return
   */
  public static <E> String getField(E e, Class<? extends Annotation> clz) {
    String value = null;
    if (e != null) {
      Class<?> eClz = e.getClass();
      Field[] fields = eClz.getDeclaredFields();
      if (fields != null) {
        for (Field f : fields) {
          f.setAccessible(true);
          Annotation annotation = f.getAnnotation(clz);
          if (annotation != null) {
            try {
              Object val = f.get(e);
              value = val.toString();
            } catch (IllegalAccessException ex) {
              value = null;
            }
          }
        }
      }
    }

    return value;
  }

  /**
   * 展开Node
   *
   * @param sources
   * @param node
   * @return
   */
  public static List<Node> expand(List<Node> sources, Node node) {

    return null;
  }

  public static List<Node> fold(List<Node> sources, Node node) {

    return null;
  }
}
