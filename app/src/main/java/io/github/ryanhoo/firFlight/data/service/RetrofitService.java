package io.github.ryanhoo.firFlight.data.service;

import io.github.ryanhoo.firFlight.data.model.*;
import io.github.ryanhoo.firFlight.network.MultiPageResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created with Android Studio.
 * User: ryan@whitedew.me
 * Date: 3/15/16
 * Time: 11:06 PM
 * Desc: RetrofitService
 */
public interface RetrofitService {

    // Token

    @FormUrlEncoded
    @POST("/login")
    Call<Token> accessToken(@Field("email") String email, @Field("password") String password);

    @GET("/user/api_token")
    Call<Token> apiToken();

    @POST("/user/api_token")
    Call<Token> refreshApiToken();

    // User
    @GET("/user")
    Call<User> user();

    // Apps

    @GET("/apps")
    Call<List<App>> apps();

    @GET("/apps/latest/{appId}?requireApiToken=true")
    Call<AppInstallInfo> appInstallInfo(@Path("appId") String appId);

    // Notifications

    String NOTIFICATON_TYPE_SYS = "sys";
    String NOTIFICATION_TYPE_RELEASE = "release";

    @GET("/notifications")
    Call<MultiPageResponse<Message>> notifications(@Query("type") String type, @Query("page") int page);

    @GET("/notifications?type=sys")
    Call<MultiPageResponse<Message>> systemNotifications();

    @POST("/notifications/{notificationId}")
    Call<Message> markNotificationAsRead(@Path("notificationId") String notificationId, @Query("is_read") boolean isRead);

    @DELETE("/notifications/{notificationId}")
    Call<Message> deleteNotification(@Path("notificationId") String notificationId);
}
