package model.responses;

import lombok.Getter;

import java.util.Date;

@Getter
public class Token {
    public String token;
    public Date expires;
    public String status;
    public String result;
}
