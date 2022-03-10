package com.ariel.Store.JUtils;


import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.junit.ComparisonFailure;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import static org.junit.Assert.assertEquals;


public class Tokens {

    private JWT jwt;

    public String getToken(int subject, String issuer){
        //Subject is id
        //issuer is page's domain
        Signer signer = HMACSigner.newSHA256Signer("loremloremlorem");
        this.jwt = new JWT().setIssuer(issuer)
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(String.valueOf(subject))
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));

        return JWT.getEncoder().encode(this.jwt, signer);
    }

    public boolean verificationToken(String token, String subject){
        Verifier verify = HMACVerifier.newVerifier("loremloremlorem");
        if(token == null || subject == null){
            return false;
        }
        try{
            this.jwt = JWT.getDecoder().decode(token, verify);
            assertEquals(this.jwt.subject, subject);
            return true;
        } catch(Exception | ComparisonFailure e){
            return false;
        }

    }
}
