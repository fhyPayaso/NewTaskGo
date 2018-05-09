package com.hrsoft.taskgo.mvp.presenter.task;

import com.hrsoft.taskgo.R;
import com.hrsoft.taskgo.base.mvp.presenter.BasePresenter;
import com.hrsoft.taskgo.common.TaskTypeConfig;
import com.hrsoft.taskgo.mvp.contract.TaskGridContract;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskGridModel;
import com.hrsoft.taskgo.mvp.model.task.bean.TaskTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FanHongyu.
 * @since 18/4/29 16:57.
 * email fanhongyu@hrsoft.net.
 */

public class TaskGridPresenter extends BasePresenter<TaskGridContract.View> implements TaskGridContract
        .Presenter {


    public TaskGridPresenter(TaskGridContract.View view) {
        super(view);
    }

    @Override
    public void getGridList(String moduleType) {

        switch (moduleType) {
            case TaskTypeConfig.MODEL_COLLEGE:
                getCollegeModelLists();
                break;
            case TaskTypeConfig.MODEL_MONEY:
                getMoneyModelLists();
                break;
            case TaskTypeConfig.MODEL_HELP:
                getHelpModelLists();
                break;
            case TaskTypeConfig.MODEL_PROMOTION:
                getPromotionModelLists();
                break;
            case TaskTypeConfig.MODEL_OFFER:
                getOfferModelLists();
                break;
            default:
                break;
        }
    }

    /**
     * 获取大学生创业icon列表
     */
    public void getCollegeModelLists() {

        List<TaskGridModel> gridModelList = new ArrayList<>();
        //自主创业模块
        List<TaskTypeModel> businessList = new ArrayList<>();
        businessList.add(new TaskTypeModel(R.drawable.ic_school_water, "校内送水", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_WATER_SCHOOL));
        businessList.add(new TaskTypeModel(R.drawable.ic_snacks, "零食店铺", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_SNACKS));
        businessList.add(new TaskTypeModel(R.drawable.ic_express_delivery, "带领快递", TaskTypeConfig
                .COLLEGE_ENTREPRENEURSHIP_EXPRESS));
        gridModelList.add(new TaskGridModel("自主创业", businessList));

        //公益服务模块
        List<TaskTypeModel> welfareList = new ArrayList<>();
        welfareList.add(new TaskTypeModel(R.drawable.ic_e_computer, "E管家", TaskTypeConfig
                .COLLEGE_PUBLIC_GOOD_E_HELPER));
        gridModelList.add(new TaskGridModel("公益服务", welfareList));
        mView.onLoadGridListSuccess(gridModelList);
    }


    /**
     * 获取赏金任务icon列表
     */
    public void getMoneyModelLists() {

        List<TaskGridModel> gridModelList = new ArrayList<>();


        //文案策划
        List<TaskTypeModel> copyWritingList = new ArrayList<>();
        copyWritingList.add(new TaskTypeModel(R.drawable.ic_translation, "文档翻译", TaskTypeConfig
                .MONEY_COPY_WRITING_TRANSLATION));
        copyWritingList.add(new TaskTypeModel(R.drawable.ic_lover_letter, "情诗情书", TaskTypeConfig
                .MONEY_COPY_WRITING_LOVE_LETTER));
        copyWritingList.add(new TaskTypeModel(R.drawable.ic_write_article, "文章撰写", TaskTypeConfig
                .MONEY_COPY_WRITING_ARTICLE));
        copyWritingList.add(new TaskTypeModel(R.drawable.ic_edit_resume, "修改简历", TaskTypeConfig
                .MONEY_COPY_WRITING_RESUME));
        gridModelList.add(new TaskGridModel("文案策划", copyWritingList));


        //创意设计
        List<TaskTypeModel> designList = new ArrayList<>();
        designList.add(new TaskTypeModel(R.drawable.ic_design_image, "海报设计", TaskTypeConfig.MONEY_DESIGN_IMAGE));
        designList.add(new TaskTypeModel(R.drawable.ic_design_logo, "logo设计", TaskTypeConfig.MONEY_DESIGN_LOGO));
        designList.add(new TaskTypeModel(R.drawable.ic_design_ui, "UI设计", TaskTypeConfig.MONEY_DESIGN_UI));
        designList.add(new TaskTypeModel(R.drawable.ic_design_ppt, "PPT设计", TaskTypeConfig.MONEY_DESIGN_PPT));
        gridModelList.add(new TaskGridModel("创意设计", designList));


        //技术支持
        List<TaskTypeModel> technologyList = new ArrayList<>();
        technologyList.add(new TaskTypeModel(R.drawable.ic_technology_web, "网站开发", TaskTypeConfig
                .MONEY_TECHNOLOGY_WEB));
        technologyList.add(new TaskTypeModel(R.drawable.ic_technology_app, "APP开发", TaskTypeConfig
                .MONEY_TECHNOLOGY_APP));
        technologyList.add(new TaskTypeModel(R.drawable.ic_technology_wx, "公众号开发", TaskTypeConfig.MONEY_TECHNOLOGY_WX));
        technologyList.add(new TaskTypeModel(R.drawable.ic_technology_ps, "PS照片", TaskTypeConfig.MONEY_TECHNOLOGY_PS));
        technologyList.add(new TaskTypeModel(R.drawable.ic_technology_video, "视频制作", TaskTypeConfig
                .MONEY_TECHNOLOGY_VIDEO));
        gridModelList.add(new TaskGridModel("技术支持", technologyList));

        //游戏专区
        List<TaskTypeModel> gameList = new ArrayList<>();
        gameList.add(new TaskTypeModel(R.drawable.ic_game_play, "游戏代练", TaskTypeConfig.MONEY_GAME_PLAY));
        gameList.add(new TaskTypeModel(R.drawable.ic_game_account, "账号交易", TaskTypeConfig.MONEY_GAME_ACCOUNT));
        gameList.add(new TaskTypeModel(R.drawable.ic_game_equipment, "装备交易", TaskTypeConfig.MONEY_GAME_EQUIPMENT));
        gameList.add(new TaskTypeModel(R.drawable.ic_game_team, "游戏组队", TaskTypeConfig.MONEY_GAME_TEAM));
        gridModelList.add(new TaskGridModel("游戏专区", gameList));


        mView.onLoadGridListSuccess(gridModelList);
    }


    /**
     * 获取校园互助icon列表
     */
    public void getHelpModelLists() {

        List<TaskGridModel> gridModelList = new ArrayList<>();


        //代买代送
        List<TaskTypeModel> behalfList = new ArrayList<>();
        behalfList.add(new TaskTypeModel(R.drawable.ic_behalf_buy, "代买物品", TaskTypeConfig.HELP_BEHALF_BUY));
        behalfList.add(new TaskTypeModel(R.drawable.ic_behalf_send, "代送物品", TaskTypeConfig.HELP_BEHALF_SEND));
        gridModelList.add(new TaskGridModel("代买代送", behalfList));


        //物品任务
        List<TaskTypeModel> thingList = new ArrayList<>();
        thingList.add(new TaskTypeModel(R.drawable.ic_thing_found, "失物招领", TaskTypeConfig.HELP_THING_FOUND));
        thingList.add(new TaskTypeModel(R.drawable.ic_thing_lost, "寻物启事", TaskTypeConfig.HELP_THING_LOST));
        gridModelList.add(new TaskGridModel("物品任务", thingList));

        //运动锻炼
        List<TaskTypeModel> sportList = new ArrayList<>();
        sportList.add(new TaskTypeModel(R.drawable.ic_sport_basketball, "打篮球", TaskTypeConfig.HELP_SPORT_BASKETBALL));
        sportList.add(new TaskTypeModel(R.drawable.ic_sport_run, "约跑步", TaskTypeConfig.HELP_SPORT_RUN));
        gridModelList.add(new TaskGridModel("运动锻炼", sportList));

        //比赛组队
        List<TaskTypeModel> competitionList = new ArrayList<>();
        competitionList.add(new TaskTypeModel(R.drawable.ic_competition_coding, "编程比赛", TaskTypeConfig
                .HELP_COMPETITION_CODDING));
        competitionList.add(new TaskTypeModel(R.drawable.ic_competition_math, "数学建模", TaskTypeConfig
                .HELP_COMPETITION_MATH));
        competitionList.add(new TaskTypeModel(R.drawable.ic_competition_game, "电子竞技", TaskTypeConfig
                .HELP_COMPETITION_GAME));
        gridModelList.add(new TaskGridModel("比赛组队", competitionList));

        //学习交流
        List<TaskTypeModel> studyList = new ArrayList<>();
        studyList.add(new TaskTypeModel(R.drawable.ic_study_position,"自习占座",TaskTypeConfig.HELP_STUDY_POSITION));
        studyList.add(new TaskTypeModel(R.drawable.ic_study_god_help,"学霸辅导",TaskTypeConfig.HELP_STUDY_GOD_HELP));
        studyList.add(new TaskTypeModel(R.drawable.ic_study_postgraduate,"考研组队",TaskTypeConfig.HELP_STUDY_POSTGRADUATE));
        gridModelList.add(new TaskGridModel("学习交流", studyList));

        mView.onLoadGridListSuccess(gridModelList);
    }


    /**
     * 获取推广任务icon列表
     */
    public void getPromotionModelLists() {

        List<TaskGridModel> gridModelList = new ArrayList<>();

        //教育机构
        List<TaskTypeModel> eduList = new ArrayList<>();
        eduList.add(new TaskTypeModel(R.drawable.ic_edu_school, "教育机构招生", TaskTypeConfig.PROMOTION_EDU_SCHOOL));
        eduList.add(new TaskTypeModel(R.drawable.ic_edu_driving_school, "驾校招生", TaskTypeConfig.PROMOTION_EDU_DRIVING));
        gridModelList.add(new TaskGridModel("教育培训", eduList));

        //生活娱乐
        List<TaskTypeModel> entertainmentList = new ArrayList<>();
        entertainmentList.add(new TaskTypeModel(R.drawable.ic_entertainment_netbar, "网吧", TaskTypeConfig
                .PROMOTION_ENTERTAINMENT_NET_BAR));
        entertainmentList.add(new TaskTypeModel(R.drawable.ic_entertainment_billiards, "台球厅", TaskTypeConfig
                .PROMOTION_ENTERTAINMENT_BILLIARDS));
        entertainmentList.add(new TaskTypeModel(R.drawable.ic_entertainment_gym, "健身房", TaskTypeConfig
                .PROMOTION_ENTERTAINMENT_GYM));
        gridModelList.add(new TaskGridModel("生活娱乐", entertainmentList));

        mView.onLoadGridListSuccess(gridModelList);
    }


    /**
     * 获取校园招聘icon列表
     */
    public void getOfferModelLists() {

        List<TaskGridModel> gridModelList = new ArrayList<>();


        //兼职招聘
        List<TaskTypeModel> partTimeList = new ArrayList<>();
        partTimeList.add(new TaskTypeModel(R.drawable.ic_part_time_teacher, "家教招聘", TaskTypeConfig
                .OFFER_PART_TIME_TEACHER));
        partTimeList.add(new TaskTypeModel(R.drawable.ic_part_time_flyer, "发传单", TaskTypeConfig
                .OFFER_PART_TIME_FLYER));
        gridModelList.add(new TaskGridModel("兼职招聘", partTimeList));


        //实习招聘
        List<TaskTypeModel> practiceList = new ArrayList<>();
        practiceList.add(new TaskTypeModel(R.drawable.ic_practice_east_soft, "东软招聘", TaskTypeConfig
                .OFFER_PRACTICE_EAST_SOFT));
        gridModelList.add(new TaskGridModel("实习招聘", practiceList));


        mView.onLoadGridListSuccess(gridModelList);
    }


}
