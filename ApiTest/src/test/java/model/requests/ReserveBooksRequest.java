package model.requests;

import java.util.ArrayList;

public class ReserveBooksRequest {
    public String userId;
    public ArrayList<Isbn> collectionOfIsbns;

    public ReserveBooksRequest(String userId, Isbn isbn) {
        this.userId = userId;
        this.collectionOfIsbns = new ArrayList<>();
        this.collectionOfIsbns.add(isbn);
    }
}
