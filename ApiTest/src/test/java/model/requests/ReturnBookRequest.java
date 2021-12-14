package model.requests;

public class ReturnBookRequest {
    public String userId;
    public String isbn;

    public ReturnBookRequest(String userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }
}
