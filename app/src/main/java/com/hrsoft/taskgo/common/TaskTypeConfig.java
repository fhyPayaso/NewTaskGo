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


    /*--------------------------*/

    /**
     * 公益服务
     */
    public static final String COLLEGE_PUBLIC_GOOD = MODEL_COLLEGE + "_public_good";

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
    public static final String MONEY_COPY_WRITING_TRANSLATION = MONEY_COPY_WRITING + "_translation";

    /**
     * 情诗情书
     */
    public static final String MONEY_COPY_WRITING_LOVE_LETTER = MONEY_COPY_WRITING + "_love_letter";


    /**
     * 文章撰写
     */
    public static final String MONEY_COPY_WRITING_ARTICLE = MONEY_COPY_WRITING + "_article";

    /**
     * 简历修改
     */
    public static final String MONEY_COPY_WRITING_RESUME = MONEY_COPY_WRITING + "_resume";


    //--------------------------//

    /**
     * 创意设计
     */
    public static final String MONEY_DESIGN = MODEL_MONEY + "_design";

    /**
     *
     */
    public static final String MONEY_DESIGN_IMAGE = MONEY_DESIGN + "_image";

    /**
     * 情诗情书
     */
    public static final String MONEY_DESIGN_PPT = MONEY_DESIGN + "_ppt";


    /**
     * 文章撰写
     */
    public static final String MONEY_DESIGN_UI = MONEY_DESIGN + "_ui";

    /**
     * 简历修改
     */
    public static final String MONEY_DESIGN_LOGO = MONEY_DESIGN + "_logo";


    //--------------------------//

    /**
     * 技术支持模块
     */
    public static final String MONEY_TECHNOLOGY = MODEL_MONEY + "_technology";

    /**
     * 网站开发
     */
    public static final String MONEY_TECHNOLOGY_WEB = MONEY_TECHNOLOGY + "_web";

    /**
     * app开发
     */
    public static final String MONEY_TECHNOLOGY_APP = MONEY_TECHNOLOGY + "_app";


    /**
     * 文章撰写
     */
    public static final String MONEY_TECHNOLOGY_PS = MONEY_TECHNOLOGY + "_ps";

    /**
     * 简历修改
     */
    public static final String MONEY_TECHNOLOGY_WX = MONEY_TECHNOLOGY + "_wx";


    /**
     * 简历修改
     */
    public static final String MONEY_TECHNOLOGY_VIDEO = MONEY_TECHNOLOGY + "_video";


    //--------------------------//

    /**
     * 游戏模块
     */
    public static final String MONEY_GAME = MODEL_MONEY + "_game";


    /**
     * 游戏组队
     */
    public static final String MONEY_GAME_TEAM = "_team";

    /**
     * 账号交易
     */
    public static final String MONEY_GAME_ACCOUNT = "_account";

    /**
     * 装备交易
     */
    public static final String MONEY_GAME_EQUIPMENT = "_equipment";

    /**
     * 游戏代练
     */
    public static final String MONEY_GAME_PLAY = "_play";


    /***************************校园互助***********************************************************/

    /**
     * 代买代送
     */
    public static final String HELP_BEHALF = MODEL_HELP + "_behalf";

    /**
     * 代送物品
     */
    public static final String HELP_BEHALF_SEND = HELP_BEHALF + "_send";

    /**
     * 代买物品
     */
    public static final String HELP_BEHALF_BUY = HELP_BEHALF + "_buy";


    //--------------------------//

    /**
     * 物品任务
     */
    public static final String HELP_THING = MODEL_HELP + "_thing";

    /**
     * 寻物启事
     */
    public static final String HELP_THING_LOST = HELP_THING + "_lost";

    /**
     * 失物招领
     */
    public static final String HELP_THING_FOUND = HELP_THING + "_found";


    //--------------------------//


    /**
     * 运动锻炼
     */
    public static final String HELP_SPORT = MODEL_HELP + "_sport";

    /**
     * 约跑步
     */
    public static final String HELP_SPORT_RUN = HELP_SPORT + "_run";

    /**
     * 约打篮球
     */
    public static final String HELP_SPORT_BASKETBALL = HELP_SPORT + "_basketball";


    //--------------------------//


    /**
     * 学习交流
     */
    public static final String HELP_STUDY = MODEL_HELP + "_study";

    /**
     * 学霸辅导
     */
    public static final String HELP_STUDY_GOD_HELP = HELP_STUDY + "_god_help";

    /**
     * 考研组队
     */
    public static final String HELP_STUDY_POSTGRADUATE = HELP_STUDY + "_postgraduate";

    /**
     * 自习占座
     */
    public static final String HELP_STUDY_POSITION = HELP_STUDY + "_position";

    //--------------------------//


    /**
     * 竞赛组队
     */
    public static final String HELP_COMPETITION = MODEL_HELP + "_competition";

    /**
     * 编程比赛
     */
    public static final String HELP_COMPETITION_CODDING = HELP_COMPETITION + "_coding";

    /**
     * 数学建模
     */
    public static final String HELP_COMPETITION_MATH = HELP_COMPETITION + "_math";

    /**
     * 电子竞技
     */
    public static final String HELP_COMPETITION_GAME = HELP_COMPETITION + "_game";


    /***************************校园推广***********************************************************/


    /**
     * 教育机构
     */
    public static final String PROMOTION_EDU = MODEL_PROMOTION + "_edu";

    /**
     * 学校机构
     */
    public static final String PROMOTION_EDU_SCHOOL = PROMOTION_EDU + "_school";

    /**
     * 驾校机构
     */
    public static final String PROMOTION_EDU_DRIVING = PROMOTION_EDU + "_driving";

    //--------------------------//


    /**
     * 生活娱乐
     */
    public static final String PROMOTION_ENTERTAINMENT = MODEL_PROMOTION + "_entertainment";


    /**
     * 网吧推广
     */
    public static final String PROMOTION_ENTERTAINMENT_NET_BAR = PROMOTION_ENTERTAINMENT + "_netBar";

    /**
     * 健身房推广
     */
    public static final String PROMOTION_ENTERTAINMENT_GYM = PROMOTION_ENTERTAINMENT + "_gym";


    /**
     * 台球室推广
     */
    public static final String PROMOTION_ENTERTAINMENT_BILLIARDS = PROMOTION_ENTERTAINMENT + "_billiards";


    /***************************校园招聘***********************************************************/


    /**
     * 兼职招聘
     */
    public static final String OFFER_PART_TIME = "_part_time";


    /**
     * 招收家教
     */
    public static final String OFFER_PART_TIME_TEACHER = OFFER_PART_TIME + "_teacher";


    /**
     * 发传单
     */
    public static final String OFFER_PART_TIME_FLYER = OFFER_PART_TIME + "_flyer";


    //--------------------------//

    /**
     * 实习招聘
     */
    public static final String OFFER_PRACTICE = "_practice";


    /**
     * 东软招聘
     */
    public static final String OFFER_PRACTICE_EAST_SOFT = OFFER_PRACTICE + "_east_soft";


}
