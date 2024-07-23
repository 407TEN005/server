package sixgarlic.potenday.global.auth;

import java.util.Map;

public class KakaoResponse {

    private final Map<String, Object> properties;

    private final Map<String, Object> account;

    private final String providerId;

    public KakaoResponse(Map<String, Object> attributes) {
        this.properties = (Map<String, Object>) attributes.get("properties");
        this.account = (Map<String, Object>) attributes.get("kakao_account");
        this.providerId = attributes.get("id").toString();
    }

    public String getProvider() {
        return "kakao";
    }

    public String getProviderId() {
        return this.providerId;
    }

    public String getName() {
        return this.properties.get("nickname").toString();
    }
}
