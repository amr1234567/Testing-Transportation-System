package Models.Response;

import java.util.Date;

public class TokenResponseModel {
    public String token;
    public Date tokenExpiration;
    public TokenResponseModel(String Token,Date tokenExpiration){
        this.tokenExpiration=tokenExpiration;
        this.token =Token;
    }
}
