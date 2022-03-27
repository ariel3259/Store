package com.ariel.Store.Utils;


import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.junit.ComparisonFailure;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import static org.junit.Assert.assertEquals;


@Component
public class Tokens {

    private JWT jwt;

    public String getToken(String subject ){
        //Subject is dni
        //issuer is page's domain
        Signer signer = HMACSigner.newSHA256Signer("loremloremlorem");
        this.jwt = new JWT().setIssuer("http://localhost:3000")
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(subject)
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
            System.out.println(e);
            return false;
        }

    }
}
