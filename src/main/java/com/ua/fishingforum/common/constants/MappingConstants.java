package com.ua.fishingforum.common.constants;

public class MappingConstants {


    public static final String UPLOAD_USER_PROFILE_IMAGE = "/uploadImage";
    public static final String UPLOAD_IMAGE = "/post/uploadImage/{postId}";
    public static final String EDIT_USER_PROFILE_MAPPING = "/edit/{nickname}";
    public static final String NEWS_BY_DATE_MAPPING = "/date";
    public static final String NEWS_BY_LIKES_MAPPING = "/likes";
    public static final String CREATE_POST_MAPPING = "/post/create";
    public static final String ALL_POSTS_FOR_CURRENT_USER_MAPPING = "/posts";
    public static final String EDIT_POST_MAPPING = "/post/edit/{postId}";
    public static final String ADD_LIKE_TO_POST_MAPPING = "/post/{postId}";
    public static final String ADD_LIKE_TO_COMMENT_MAPPING = "/comment/{id}";
    public static final String FIND_ALL_POST_LIKES_MAPPING = "/post/{postId}";
    public static final String FIND_ALL_COMMENT_LIKES_MAPPING = "/comment/{commentId}";
    public static final String DELETE_POST_LIKE_MAPPING = "/post/{postId}";
    public static final String DELETE_COMMENT_LIKE_MAPPING = "/comment/{commentId}";
    public static final String LOGIN_MAPPING = "/access_token";
    public static final String REGISTRATION_MAPPING = "/register";
    private MappingConstants() {
    }
}
