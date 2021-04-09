package com.example.moviebuff;

public class ItemClass {

    private String imageView;
    private String textView;
    private String description;
    private String releaseDate;
    private String rating;
    private String reviews;

    public ItemClass(String imageView, String textView,String description,String releaseDate,String rating,String reviews) {
        this.imageView = imageView;
        this.textView = textView;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public String getImageView() {
        return imageView;
    }

    public String getTextView() {
        return textView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public String getReviews() {
        return reviews;
    }
}
