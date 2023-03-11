package icu.saymeevetime.backend.properties;

import lombok.Data;

/**
 * @author jiacheng
 * @date 12/3/23 2:41 am 星期日
 * @description :
 */
@Data
public class JwtProperties {
    public static JwtProperties jwtProperties = new JwtProperties();

    public JwtProperties() {
    }

    public static JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    public static final String JWT_PREFIX = "jwt";

    public String header = "Authorization";

    public String secret = "defaultSecret";

    public Long expiation = 604800L;

    public String authPath = "login";

    public String md5Key = "randomKey";

    public static String getJwtPrefix() {
        return JWT_PREFIX;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiation() {
        return expiation;
    }

    public void setExpiation(Long expiation) {
        this.expiation = expiation;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getSecret() {
        return secret;
    }

    public String getAuthPath() {
        return authPath;
    }

    public String getMd5Key() {
        return md5Key;
    }


}
