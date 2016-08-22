package com.sina.young.jiandandemo1.bean;

import java.util.List;

/**
 * Created by zhaoyang on 16/8/22.
 */
public class NewsBean {

    /**
     * date : 2016-08-21 16:00:14
     * comment_count : 15
     * author : {"name":"卤鸡爪子","nickname":"卤鸡爪子","last_name":"","description":"","id":605,"first_name":"","slug":"paw","url":""}
     * custom_fields : {"thumb_c":["http://tankr.net/s/custom/5KFL.jpg"]}
     * id : 81756
     * title : 怎样把狗带到你的婚礼上
     * url : http://i.jandan.net/2016/08/21/pet-your-wedding.html
     * tags : [{"description":"","id":693,"post_count":687,"title":"小贴士","slug":"%e5%b0%8f%e8%b4%b4%e5%a3%ab"}]
     */
    private String date;
    private int comment_count;
    private AuthorEntity author;
    private Custom_fieldsEntity custom_fields;
    private int id;
    private String title;
    private String url;
    private List<TagsEntity> tags;

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public void setCustom_fields(Custom_fieldsEntity custom_fields) {
        this.custom_fields = custom_fields;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public int getComment_count() {
        return comment_count;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public Custom_fieldsEntity getCustom_fields() {
        return custom_fields;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public class AuthorEntity {
        /**
         * name : 卤鸡爪子
         * nickname : 卤鸡爪子
         * last_name :
         * description :
         * id : 605
         * first_name :
         * slug : paw
         * url :
         */
        private String name;
        private String nickname;
        private String last_name;
        private String description;
        private int id;
        private String first_name;
        private String slug;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getNickname() {
            return nickname;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getSlug() {
            return slug;
        }

        public String getUrl() {
            return url;
        }
    }

    public class Custom_fieldsEntity {
        /**
         * thumb_c : ["http://tankr.net/s/custom/5KFL.jpg"]
         */
        private List<String> thumb_c;

        public void setThumb_c(List<String> thumb_c) {
            this.thumb_c = thumb_c;
        }

        public List<String> getThumb_c() {
            return thumb_c;
        }
    }

    public class TagsEntity {
        /**
         * description :
         * id : 693
         * post_count : 687
         * title : 小贴士
         * slug : %e5%b0%8f%e8%b4%b4%e5%a3%ab
         */
        private String description;
        private int id;
        private int post_count;
        private String title;
        private String slug;

        public void setDescription(String description) {
            this.description = description;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPost_count(int post_count) {
            this.post_count = post_count;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }

        public int getPost_count() {
            return post_count;
        }

        public String getTitle() {
            return title;
        }

        public String getSlug() {
            return slug;
        }
    }
}
