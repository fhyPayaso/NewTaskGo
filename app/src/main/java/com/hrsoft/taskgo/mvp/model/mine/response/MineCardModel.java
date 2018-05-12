package com.hrsoft.taskgo.mvp.model.mine.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author：lszr on 2018/5/12 20:50
 * @email：1085963811@qq.com
 */
public class MineCardModel {
    @SerializedName("card_id")
    private int cardId;
    private String use;
    private String picture;
    private int price;
    private String content;
    private int number;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MineCardModel(int cardId, String use, String picture, int price, String content, int number) {

        this.cardId = cardId;
        this.use = use;
        this.picture = picture;
        this.price = price;
        this.content = content;
        this.number = number;
    }
}
