package model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class Book {
    public String isbn;

    public String title;

    public String subTitle;

    public String author;

    @JsonProperty("publish_date")
    public Date publishDate;

    public String publisher;

    public int pages;

    public String description;

    public String website;
}
