package com.wingcfly.uber.Common;

import com.wingcfly.uber.Remote.IGoogleAPI;
import com.wingcfly.uber.Remote.RetrofitClient;

public class Common {
    public static final String baseUrl = "https://maps.googleapis.com";
    public static IGoogleAPI getGoogleAPI() {
        return RetrofitClient.getClient(baseUrl).create(IGoogleAPI.class);
    }
}
