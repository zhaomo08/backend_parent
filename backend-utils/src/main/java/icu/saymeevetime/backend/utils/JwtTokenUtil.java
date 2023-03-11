package icu.saymeevetime.backend.utils;

import icu.saymeevetime.backend.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * @author jiacheng
 * @date 12/3/23 2:51 am 星期日
 * @description :
 */
public class JwtTokenUtil {
    private JwtProperties jwtProperties = JwtProperties.getJwtProperties();

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 获取发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimsFromToken(token).getIssuedAt();
    }


    /**
     * 获取失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 获取接受者
     */
    public String getAudienceFromTokenFromToken(String token) {
        return getClaimsFromToken(token).getAudience();
    }

    /**
     * 获取私有的 jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimsFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key 从token 中
     */
    public String getMd5KeyFromToken(String token, String key) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {

        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token 是否正确
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }


    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成token
     */

    public String generateToken(String name, String randomKey) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, name);
    }

    private String doGenerateToken(HashMap<String, Object> claims, String subject) {
        final Date createDate = new Date();
        final Date expirationDate = new Date(createDate.getTime() + jwtProperties.getExpiation() * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public String getRandomKey() {
        return getRandomString(6);
    }

    private String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz01234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
