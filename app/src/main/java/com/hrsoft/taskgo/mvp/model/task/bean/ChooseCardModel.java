package com.hrsoft.taskgo.mvp.model.task.bean;

import com.hrsoft.taskgo.base.mvp.model.BaseBean;

/**
 * @author FanHongyu.
 * @since 18/5/5 16:34.
 * email fanhongyu@hrsoft.net.
 */

public class ChooseCardModel extends BaseBean {


    private String cardName;
    private String cardPrice;
    private int haveNumber;
    private int chooseNumber;
    private String cardInfo;
    private String cardImgUrl;


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(String cardPrice) {
        this.cardPrice = cardPrice;
    }

    public int getHaveNumber() {
        return haveNumber;
    }

    public void setHaveNumber(int haveNumber) {
        this.haveNumber = haveNumber;
    }

    public int getChooseNumber() {
        return chooseNumber;
    }

    public void setChooseNumber(int chooseNumber) {
        this.chooseNumber = chooseNumber;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getCardImgUrl() {
        return cardImgUrl;
    }

    public void setCardImgUrl(String cardImgUrl) {
        this.cardImgUrl = cardImgUrl;
    }

    @Override
    public boolean checkValue() {

        if (cardImgUrl == null) {
            return false;
        }
        return true;
    }
}
