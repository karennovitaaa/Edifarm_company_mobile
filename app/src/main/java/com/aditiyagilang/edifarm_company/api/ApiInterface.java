package com.aditiyagilang.edifarm_company.api;


import com.aditiyagilang.edifarm_company.model.ActivityPost.ActivityPost;
import com.aditiyagilang.edifarm_company.model.AddComment.AddComment;
import com.aditiyagilang.edifarm_company.model.AddRating.AddRating;
import com.aditiyagilang.edifarm_company.model.AddReason.AddReason;
import com.aditiyagilang.edifarm_company.model.CekUserRating.CekUserRating;
import com.aditiyagilang.edifarm_company.model.ChangePassword;
import com.aditiyagilang.edifarm_company.model.ClearSession.ClearSession;
import com.aditiyagilang.edifarm_company.model.CountComment.CountComment;
import com.aditiyagilang.edifarm_company.model.CountLike.CountLike;
import com.aditiyagilang.edifarm_company.model.DeleteLike.DeleteLike;
import com.aditiyagilang.edifarm_company.model.DeletePost.DeletePost;
import com.aditiyagilang.edifarm_company.model.Download.Download;
import com.aditiyagilang.edifarm_company.model.FilterActivity.FilterActivity;
import com.aditiyagilang.edifarm_company.model.GetComment.GetComment;
import com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivity;
import com.aditiyagilang.edifarm_company.model.GetPostLike.GetPostLike;
import com.aditiyagilang.edifarm_company.model.GetPostUser.GetPostUser;
import com.aditiyagilang.edifarm_company.model.GetRate.GetRating;
import com.aditiyagilang.edifarm_company.model.History.History;
import com.aditiyagilang.edifarm_company.model.Like.Like;
import com.aditiyagilang.edifarm_company.model.OTP.OTP;
import com.aditiyagilang.edifarm_company.model.PosActivity.PostActivity;
import com.aditiyagilang.edifarm_company.model.Posting.Posting;
import com.aditiyagilang.edifarm_company.model.SearchingUser.SearchibUser;
import com.aditiyagilang.edifarm_company.model.Stalking.StalkingAcount;
import com.aditiyagilang.edifarm_company.model.UpActivity.UpActivity;
import com.aditiyagilang.edifarm_company.model.UpdateBio.UpdateBio;
import com.aditiyagilang.edifarm_company.model.UpdateSesion.UpdateSesion;
import com.aditiyagilang.edifarm_company.model.activity.Activity;
import com.aditiyagilang.edifarm_company.model.addActivity.AddActivity;
import com.aditiyagilang.edifarm_company.model.addSession.AddSession;
import com.aditiyagilang.edifarm_company.model.deleteActivity.DeleteActivity;
import com.aditiyagilang.edifarm_company.model.deleteSession.DeleteSession;
import com.aditiyagilang.edifarm_company.model.documentation.Documentation;
import com.aditiyagilang.edifarm_company.model.getLike.GetLike;
import com.aditiyagilang.edifarm_company.model.getSession.GetSession;
import com.aditiyagilang.edifarm_company.model.login.Login;
import com.aditiyagilang.edifarm_company.model.pass.Pas;
import com.aditiyagilang.edifarm_company.model.register.Register;
import com.aditiyagilang.edifarm_company.model.sharelink.Share;
import com.aditiyagilang.edifarm_company.model.updateactivity.UpdateActivitys;
import com.aditiyagilang.edifarm_company.ui.DashboardModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<Login> loginresponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("fcm_token") String fcm_token

    );

    @FormUrlEncoded
    @POST("register")
    Call<Register> registerresponse(
            @Field("username") String username,
            @Field("name") String name,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("born_date") String born_date,
            @Field("email") String email,
            @Field("confirm_password") String confirm_password,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );

    @Multipart
    @POST("update")
    Call<UpdateBio> updateResponse(
            @Part("user_id") RequestBody user_id,
            @Part("username") RequestBody username,
            @Part("name") RequestBody name,
            @Part("address") RequestBody address,
            @Part("phone") RequestBody phone,
            @Part("born_date") RequestBody born_date,
            @Part("email") RequestBody email,
            @Part MultipartBody.Part photo
    );

    @FormUrlEncoded
    @POST("getact")
    Call<Activity> actResponse(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("deleteData")
    Call<DeleteActivity> deleteactResponse(
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("getActFull")
    Call<GetFullActivity> actFullResponse(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("checkEmail")
    Call<Pas> checkEmailResponse(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("checkOtp")
    Call<OTP> checkOtpResponse(
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("gantiPassword")
    Call<ChangePassword> gantiPasswordResponse(
            @Field("otp") String otp,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password
    );

    @FormUrlEncoded
    @POST("getpost")
    Call<DashboardModel> GetPostResponse(
            @Field("user_id") String user_id
    );

    @POST("getPostActivity")
    Call<ActivityPost> getPostActivityPostResponse(

    );

    @FormUrlEncoded
    @POST("filterActivity")
    Call<Activity> actFilterResponse(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("updateStatus")
    Call<UpActivity> UpactResponse(
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("getses")
    Call<GetSession> getsesResponse(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("getAllDocumentations")
    Call<History> getAllDocumentationsResponse(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("generateReport")
    Call<Documentation> generateReportResponse(
            @Field("user_id") String user_id,
            @Field("session_id") String session_id

    );

    @FormUrlEncoded
    @POST("updateStatusSession")
    Call<ClearSession> updateStatusSessionResponse(
            @Field("id") String id,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("downloadPDF")
    Call<Download> downloadPDFResponse(
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("addActivity")
    Call<AddActivity> CreatActResponse(
            @Field("activity_name") String activity_name,
            @Field("status") String status,
            @Field("start") String start,
            @Field("end") String end,
            @Field("session_id") String session_id
    );

    @FormUrlEncoded
    @POST("filterActivity")
    Call<FilterActivity> filterActivityResponse(
            @Field("user_id") String user_id,
            @Field("search") String search
    );

    @FormUrlEncoded
    @POST("getPostsByUsername")
    Call<SearchibUser> getPostsByUsernameResponse(
            @Field("search") String search
    );

    @FormUrlEncoded
    @POST("updateActivity")
    Call<UpdateActivitys> UpdateActResponse(
            @Field("id") String id,
            @Field("activity_name") String activity_name,
            @Field("start") String start,
            @Field("end") String end
    );

    @FormUrlEncoded
    @POST("updateSession")
    Call<UpdateSesion> UpdateSesResponse(
            @Field("id") String id,
            @Field("status") String status,
            @Field("start") String start,
            @Field("end") String end

    );

    @FormUrlEncoded
    @POST("addSession")
    Call<AddSession> AddSessionResponse(
            @Field("plant_name") String plant_name,
            @Field("start") String start,
            @Field("end") String end,
            @Field("user_id") String user_id
    );


    @Multipart
    @POST("post")
    Call<Posting> postResponse(
            @Part("caption") RequestBody caption,
            @Part("post_latitude") RequestBody latitude,
            @Part("post_longitude") RequestBody longitude,
            @Part("user_id") RequestBody userId,
            @Part MultipartBody.Part image
    );


    @FormUrlEncoded
    @POST("getSessionByUserId")
    Call<GetSession> GetSessionResponse(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("addLike")
    Call<Like> LikeResponse(
            @Field("user_id") String user_id,
            @Field("post_id") String post_id,
            @Field("fcm_token") String fcm_token
    );

    @FormUrlEncoded
    @POST("getLike")
    Call<GetLike> GetLikeResponse(
            @Field("user_id") String user_id,
            @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("deleteLikeByPostId")
    Call<DeleteLike> DeleteLikeResponse(
            @Field("post_id") String post_id,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("countLikesByPostId")
    Call<CountLike> CountLikeResponse(
            @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("getCommentsByPostId")
    Call<GetComment> getCommentsByPostIdResponse(
            @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("countUserByPost")
    Call<CountComment> countUserByPostResponse(
            @Field("post_id") String post_id
    );

    @FormUrlEncoded
    @POST("addComment")
    Call<AddComment> addCommentResponse(
            @Field("post_id") String post_id,
            @Field("comment") String comment,
            @Field("user_id") String user_id,
            @Field("fcm_token") String fcm_token
    );

    @FormUrlEncoded
    @POST("addReason")
    Call<AddReason> addReasonResponse(
            @Field("post_id") String post_id,
            @Field("reason") String reason,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("getLikesByUserId")
    Call<GetPostLike> getPostlikeResponse(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("getpoststalk")
    Call<StalkingAcount> getpoststalkResponse(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("deletePostByPostId")
    Call<DeletePost> deletePostByPostIdkResponse(
            @Field("post_id") String user_id
    );

    @FormUrlEncoded
    @POST("createShareableLink")
    Call<Share> createShareableLinkResponse(
            @Field("post_id") String user_id
    );

    @FormUrlEncoded
    @POST("getPostsByUserId")
    Call<GetPostUser> getPostUserResponse(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("deleteStatusSession")
    Call<DeleteSession> DeleteSessionResponse(
            @Field("user_id") String user_id,
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("addPostActivity")
    Call<PostActivity> addPostActivityResponse(
            @Field("user_id") String user_id,
            @Field("session_id") String id,
            @Field("pdf_file") String post_file
    );

    @FormUrlEncoded
    @POST("addRating")
    Call<AddRating> addRatingResponse(
            @Field("user_id") String user_id,
            @Field("post_activity_id") String post_activity_id,
            @Field("rate") int rate
    );

    @FormUrlEncoded
    @POST("calculateAverageRating")
    Call<GetRating> calculateAverageRatingResponse(
            @Field("post_activity_id") String post_activity_id
    );

    @FormUrlEncoded
    @POST("checkUserRating")
    Call<CekUserRating> checkUserRatingResponse(
            @Field("user_id") String user_id,
            @Field("post_activity_id") String post_activity_id
    );
}
