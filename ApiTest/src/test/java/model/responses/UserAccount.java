package model.responses;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class UserAccount {
    public String userId;
    public String username;
    public ArrayList<Book> books;
}
