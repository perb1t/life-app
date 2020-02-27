package com.shijiwei.life.widget.node;

import com.shijiwei.life.widget.node.inject.NodeId;
import com.shijiwei.life.widget.node.inject.NodePid;

import java.util.List;

/** Created by shijiwei on 2020-02-25. @Desc: */
public class Node<E> {

  /* 父节点 */
  private Node<E> parent;

  /* 子节点列表 */
  private List<Node<E>> children;

  /* 是否为叶子节点 */
  private boolean isLeaf;

  /* 挂载实体类 */
  private E data;

  public Node(E data) {
    this.data = data;
  }

  public Node<E> getParent() {
    return parent;
  }

  public void setParent(Node<E> parent) {
    this.parent = parent;
  }

  public List<Node<E>> getChildren() {
    return children;
  }

  public void setChildren(List<Node<E>> children) {
    this.children = children;
  }

  public boolean isLeaf() {
    return isLeaf;
  }

  public void setLeaf(boolean leaf) {
    isLeaf = leaf;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public int getLevel() {
    return getParent() == null ? 0 : getParent().getLevel() + 1;
  }

  public String getId(){
      return NodeManager.getField(getData(), NodeId.class);
  }

  public String getPid(){
      return NodeManager.getField(getData(), NodePid.class);
  }


}
