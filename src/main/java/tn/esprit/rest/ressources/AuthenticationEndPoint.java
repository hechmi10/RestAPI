package tn.esprit.rest.ressources;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.ZoneId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Path("authentication")
public class AuthenticationEndPoint {
 @Context
 private UriInfo uriInfo;

 @POST
 @Produces(MediaType.TEXT_PLAIN)
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
     try {
         authenticate(username, password);
         String token = issueToken(username);
         return Response.ok(token).build();
     } catch (Exception e) {
         return Response.status(Response.Status.FORBIDDEN).build();
     }
 }
 private void authenticate(String username, String password) {
     System.out.println("Authenticating user...");
 }
 private String issueToken(String username) {
     String keyString = "simplekey";
     Key key = new SecretKeySpec(keyString.getBytes(),0,keyString.getBytes().length, "DES");
     System.out.println("the key is : " + key.hashCode());
     System.out.println("uriInfo.getAbsolutePath().toString() : " + uriInfo.getAbsolutePath().toString());
     System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));
     String jwtToken = Jwts.builder()
             .setSubject(username)
             .setIssuer(uriInfo.getAbsolutePath().toString())
                 .setIssuedAt(new Date())
                 .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                 .signWith(SignatureAlgorithm.HS512, key)
                 .compact();
         System.out.println("the returned token is : " + jwtToken);
         return jwtToken;
     }
     private Date toDate(LocalDateTime localDateTime) {
         return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
     }
 }
