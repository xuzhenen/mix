
# 一棵BST树

```
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

package com.taobao.basicc;

/**
 * @author JackalTsc
 */
public class MyBinSearchTree<E extends Comparable<E>> {

    public static void main(String[] args) {
        MyBinSearchTree myBinSearchTree = new MyBinSearchTree();
        myBinSearchTree.insert(45);
        myBinSearchTree.insert(24);
        myBinSearchTree.insert(53);
        myBinSearchTree.insert(12);
        myBinSearchTree.insert(37);
        myBinSearchTree.insert(93);
        myBinSearchTree.preorder(myBinSearchTree.getRoot());
        System.out.println("-------");
        myBinSearchTree.delete(24);
        myBinSearchTree.preorder(myBinSearchTree.getRoot());
    }

    // 根
    private TreeNode<E> root;

    // 默认构造函数
    public MyBinSearchTree() {}

    // 二叉查找树的搜索
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    // 二叉查找树的插入
    public boolean insert(E e) {
        // 如果之前是空二叉树 插入的元素就作为根节点
        if (root == null) {
            root = createNewNode(e);
        } else {
            // 否则就从根节点开始遍历 直到找到合适的父节点
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            // 插入
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        return true;
    }

    /**
     * Getter method for property <tt>root</tt>.
     * 
     * @return property value of root
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    // 创建新的节点
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode(e);
    }

    protected void postorder(TreeNode<E> root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.element + " ");
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.println(root.element + " ");
        inorder(root.right);
    }

    protected void preorder(TreeNode<E> root) {
        if (root == null)
            return;
        System.out.println(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // 二叉搜索树删除节点
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        // 找到要删除的节点的位置
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        // 没找到要删除的节点
        if (current == null) {
            return false;
        }
        // 考虑第一种情况
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else { // 考虑第二种情况
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            // 找到左子树中最大的元素节点
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            // 替换
            current.element = rightMost.element;
            // parentOfRightMost和rightMost左孩子相连
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        return true;
    }
}

// 二叉树的节点
class TreeNode<E extends Comparable<E>> {

    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E e) {
        element = e;
    }
}
```
