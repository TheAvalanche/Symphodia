package org.symphodia.band.service;


import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class FacebookService {

    public static void test() throws FacebookException {
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("541802519281855", "4285b774675af25c21badfa0ad56933e");
        facebook.setOAuthPermissions("publish_stream,offline_access,manage_pages");
        facebook.setOAuthAccessToken(new AccessToken("CAAHsxDqzQL8BABZABX2rhJ3tyZBLMwzvbfjDerdnSZCriJyZA60bZCSAjVTe8y7CfZCZBewyeZBtvq82Qk4NzQUYEzUvRtUDadN8GYXzUgZCkFTpUiuZAWTdnPsZCJpSufGE02yL3ZAZA2IiHDZCVx41vhQMl0AbuuRoYfzIOZCf6pMZA7ZAqFMMFZA9ChIoRbwNj1UeN3NGEwWSrqQ0XZCZBgZDZD", null));
        facebook.postStatusMessage("Test message.");
    }

    public static void main(String[] args) throws FacebookException {
        test();

    }
}
