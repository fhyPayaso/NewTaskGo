package com.hrsoft.taskgo.mvp.model.mine;

/**
 * @author：lszr on 2018/5/12 17:38
 * @email：1085963811@qq.com
 */
public class FeedBackModel {
    private String contents;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public FeedBackModel(String contents) {

        this.contents = contents;
    }
}
