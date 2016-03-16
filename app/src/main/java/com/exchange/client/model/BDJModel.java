package com.exchange.client.model;

import java.util.List;

/**
 * Created by qilin on 2016/3/11.
 */
public class BDJModel extends AbstractBaseModel{

    /**
     * pagebean : {"allNum":3793,"allPages":190,"contentlist":[{"create_time":"2015-07-02 13:00:02","hate":"111","height":"800","id":"14710774","image0":"http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image1":"http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image2":"http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image3":"http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg","is_gif":"0","love":"303","text":"哎尼玛！抢我五杀","type":"10","videotime":"0","videouri":"","voicelength":"0","voicetime":"0","voiceuri":"","weixin_url":"http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=","width":"600"}],"currentPage":1,"maxResult":20}
     * ret_code : 0
     */

    private ShowapiResBodyEntity showapi_res_body;

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public static class ShowapiResBodyEntity {
        /**
         * allNum : 3793
         * allPages : 190
         * contentlist : [{"create_time":"2015-07-02 13:00:02","hate":"111","height":"800","id":"14710774","image0":"http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image1":"http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image2":"http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg","image3":"http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg","is_gif":"0","love":"303","text":"哎尼玛！抢我五杀","type":"10","videotime":"0","videouri":"","voicelength":"0","voicetime":"0","voiceuri":"","weixin_url":"http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=","width":"600"}]
         * currentPage : 1
         * maxResult : 20
         */

        private PagebeanEntity pagebean;
        private int ret_code;

        public void setPagebean(PagebeanEntity pagebean) {
            this.pagebean = pagebean;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanEntity getPagebean() {
            return pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public static class PagebeanEntity {
            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;
            /**
             * create_time : 2015-07-02 13:00:02
             * hate : 111
             * height : 800
             * id : 14710774
             * image0 : http://ww2.sinaimg.cn/mw240/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg
             * image1 : http://ww2.sinaimg.cn/bmiddle/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg
             * image2 : http://ww2.sinaimg.cn/large/005OPYkojw1etn14eqlj8j30go0m8ab3.jpg
             * image3 : http://wimg.spriteapp.cn/ugc/2015/07/01/5592ca3b28f6b.jpg
             * is_gif : 0
             * love : 303
             * text : 哎尼玛！抢我五杀
             * type : 10
             * videotime : 0
             * videouri :
             * voicelength : 0
             * voicetime : 0
             * voiceuri :
             * weixin_url : http://14710774.f.budejie.com/budejie/land.php?pid=14710774&wx.qq.com&appname=
             * width : 600
             */

            private List<ContentlistEntity> contentlist;

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public void setContentlist(List<ContentlistEntity> contentlist) {
                this.contentlist = contentlist;
            }

            public int getAllNum() {
                return allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public List<ContentlistEntity> getContentlist() {
                return contentlist;
            }

            public static class ContentlistEntity {
                private String create_time;
                private String hate;
                private String height;
                private String id;
                private String image0;
                private String image1;
                private String image2;
                private String image3;
                private String is_gif;
                private String love;
                private String text;
                private String type;
                private String videotime;
                private String videouri;
                private String voicelength;
                private String voicetime;
                private String voiceuri;
                private String weixin_url;
                private String width;

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public void setHate(String hate) {
                    this.hate = hate;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setImage0(String image0) {
                    this.image0 = image0;
                }

                public void setImage1(String image1) {
                    this.image1 = image1;
                }

                public void setImage2(String image2) {
                    this.image2 = image2;
                }

                public void setImage3(String image3) {
                    this.image3 = image3;
                }

                public void setIs_gif(String is_gif) {
                    this.is_gif = is_gif;
                }

                public void setLove(String love) {
                    this.love = love;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setVideotime(String videotime) {
                    this.videotime = videotime;
                }

                public void setVideouri(String videouri) {
                    this.videouri = videouri;
                }

                public void setVoicelength(String voicelength) {
                    this.voicelength = voicelength;
                }

                public void setVoicetime(String voicetime) {
                    this.voicetime = voicetime;
                }

                public void setVoiceuri(String voiceuri) {
                    this.voiceuri = voiceuri;
                }

                public void setWeixin_url(String weixin_url) {
                    this.weixin_url = weixin_url;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public String getHate() {
                    return hate;
                }

                public String getHeight() {
                    return height;
                }

                public String getId() {
                    return id;
                }

                public String getImage0() {
                    return image0;
                }

                public String getImage1() {
                    return image1;
                }

                public String getImage2() {
                    return image2;
                }

                public String getImage3() {
                    return image3;
                }

                public String getIs_gif() {
                    return is_gif;
                }

                public String getLove() {
                    return love;
                }

                public String getText() {
                    return text;
                }

                public String getType() {
                    return type;
                }

                public String getVideotime() {
                    return videotime;
                }

                public String getVideouri() {
                    return videouri;
                }

                public String getVoicelength() {
                    return voicelength;
                }

                public String getVoicetime() {
                    return voicetime;
                }

                public String getVoiceuri() {
                    return voiceuri;
                }

                public String getWeixin_url() {
                    return weixin_url;
                }

                public String getWidth() {
                    return width;
                }
            }
        }
    }
}
