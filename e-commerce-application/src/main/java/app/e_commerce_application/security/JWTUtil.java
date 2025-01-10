package app.e_commerce_application.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JWTUtil {

	// @Value("${spring.application.jwt.secret}")
	private String secret = "BAT_CHANH_DAO";

    @Value("${spring.application.jwt.expiration}")
    private long expiration;

	public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {
		return JWT.create()
				.withClaim("username", username)
				.withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
				.sign(Algorithm.HMAC256(secret));
	}
	
	public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
		DecodedJWT jwt = verifier.verify(token);
        System.out.println("jwt.getClaim(\"username\").asString(): " + jwt.getClaim("username").asString());
        System.out.println("jwt.getClaim(\"role\").asString(): " + jwt.getClaim("role").asString());
		return jwt.getClaim("role").asString();
		// return jwt.getClaim("username").asString();
	}
}