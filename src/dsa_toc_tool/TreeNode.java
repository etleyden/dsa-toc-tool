package dsa_toc_tool;

import java.util.ArrayList;

public class TreeNode {
    private ArrayList<TreeNode> children;
    private TreeNode parent;

    private String value;

    public TreeNode(String value) {
        this.value = value;
    }

    public TreeNode(TreeNode parent, String value) {
        this.parent = parent;
        this.value = value;
    }

    public void addChild(TreeNode node) {
        children.add(node);
    }

    public TreeNode getNthChild(int n) {
        return children.get(n);
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

}
