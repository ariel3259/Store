package com.ariel.Store.Utils;


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

    public String getToken(String id ){
        //Subject is id
        //issuer is page's domain
        Signer signer = HMACSigner.newSHA256Signer("loremloremlorem");
        this.jwt = new JWT().setIssuer("http://localhost:3000")
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(String.valueOf(id))
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));

        return JWT.getEncoder().encode(this.jwt, signer);
    }

    public boolean verificationToken(String token, String id){
        Verifier verify = HMACVerifier.newVerifier("loremloremlorem");
        if(token == null || id == null){
            System.out.println("No hay datos");
            return false;
        }
        System.out.println(id);
        try{
            this.jwt = JWT.getDecoder().decode(token, verify);
            assertEquals(this.jwt.subject, id);
            return true;
        } catch(Exception | ComparisonFailure e){
            System.out.println(e);
            return false;
        }

    }
}
