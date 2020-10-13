package me.qping.common.model;

import java.text.Collator;
import java.util.List;

/**
 * Created by QPing on 2015/12/28.
 */
public class TreeModel implements Comparable<TreeModel>{
    String id;
    String text;
    String value;
    String icon;
    String remark;
    int level;
    boolean isLeaf;
    List<TreeModel> children;

    public TreeModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }

    @Override
    public int compareTo(TreeModel o) {
        if(this ==o){
            return 0;
        }
        if (o != null) {
            if(this.isLeaf() == false && o.isLeaf() == true){
                return -1;
            }
            if(this.isLeaf() == true && o.isLeaf() == false){
                return 1;
            }
            Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
            return cmp.compare(this.getText(),o.getText());
        }else{
            return Integer.MIN_VALUE;
        }
    }
}
