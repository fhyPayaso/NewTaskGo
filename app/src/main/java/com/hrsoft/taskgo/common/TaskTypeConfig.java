package com.hrsoft.taskgo.common;


/**
 * 任务类型
 *
 * @author FanHongyu.
 * @since 18/4/29 15:03.
 * email fanhongyu@hrsoft.net.
 */

public class TaskTypeConfig {

    /**
     * 模块bundleKey
     */
    public static final String KEY_MODULE_TYPE = "key_model_type";

    /**
     * 任务类型bundleKey
     */
    public static final String KEY_TASK_TYPE = "key_task_type";


    /**
     * 大学生创新创业
     */
    public static final String MODEL_COLLEGE = "college";


    /**
     * 自定义任务
     */
    public static final String MODEL_DIY = "diy";


    /**
     * 赏金任务
     */
    public static final String MODEL_MONEY = "money";


    /**
     * 校园互助
     */
    public static final String MODEL_HELP = "help";

    /**
     * 校园推广
     */
    public static final String MODEL_PROMOTION = "promotion";

    /**
     * 校园招聘
     */
    public static final String MODEL_OFFER = "offer";


    /*****************************************大学生创新创业*****************************************/


    /**
     * 自主创业
     */
    public static final String COLLEGE_ENTREPRENEURSHIP = MODEL_COLLEGE + "_entrepreneurship";


    /**
     * 公益服务
     */
    public static final String COLLEGE_PUBLIC_GOOD = MODEL_COLLEGE + "_public_good";


    /**
     * 校内送水
     */
    public static final String COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL = COLLEGE_ENTREPRENEURSHIP +
            "_water_school";


    /**
     * 带领快递
     */
    public static final String COLLEGE_ENTREPRENEURSHIP_EXPRESS = COLLEGE_ENTREPRENEURSHIP + "_express";


    /**
     * 零食店铺
     */
    public static final String COLLEGE_ENTREPRENEURSHIP_SNACKS = COLLEGE_ENTREPRENEURSHIP + "_snacks";


    /**
     * e管家
     */
    public static final String COLLEGE_PUBLIC_GOOD_E_HELPER = COLLEGE_PUBLIC_GOOD + "_e_helper";


    /***************************赏金任务***********************************************************/


    /**
     * 文案策划
     */
    public static final String MONEY_COPY_WRITING = MODEL_MONEY + "_copyWriting";

    /**
     * 文档翻译
     */
    public static final String MONYE_COPY_WRITING_TRANSLATION = MONEY_COPY_WRITING + "_translation";

    /**
     * 情诗情书
     */
    public static final String MONYE_COPY_WRITING_LOVE_LETTER = MONEY_COPY_WRITING + "_love_letter";


    public static final String MONYE_COPY_WRITING_ = MONEY_COPY_WRITING + "";

}
