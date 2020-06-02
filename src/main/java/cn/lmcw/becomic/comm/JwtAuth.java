package cn.lmcw.becomic.comm;

import cn.lmcw.becomic.utils.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtAuth {
    /**
     * 生成加密的 秘钥
     *
     * @return cV4Lu59UQGWTMgZNO0wC+jUzCFfgq/lRFHr/qmVU3zs=
     */
    public String createSecretString() {
        return Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    }

    /**
     * @param secret  秘钥
     * @param expire  多少秒后过期
     * @param content 签名的内容
     * @return 加密 token
     */
    private String sign(String secret, String content, long expire) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        JwtBuilder jwtBuilder = Jwts.builder().setSubject(content);
        if (expire <= 0) {
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + expire * 1000));
        }
        return Jwts.builder().setSubject(content).signWith(key).compact();
    }

    public String sign(String secret, String content) {

        return sign(secret, content, 0);
    }

    public String decrypt(String secret, String signStr) throws Exception {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(signStr);
        return claimsJws.getBody().getSubject();
    }

    /**
     * @param secret
     * @param signStr
     * @param content
     * @return 200:通过 3000:过期 500:不合法 3001:验证不通过
     */
    public int auth(String secret, String signStr, String content) {
        int status = 200;
        try {
            String subject = decrypt(secret, signStr);
            status = (!StringUtils.isEmpty(subject) && subject.equals(content)) ? status : 3001;
        } catch (ExpiredJwtException e) {
            status = 3000;
        } catch (Exception e) {
            status = 500;
        }
        return status;
    }
}
