package com.example.bookshop.dto;

import java.time.LocalDate;

/*private int id;
    private String title;
    private double price;
    private String author;
    private String imgurl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publisDate;
    @Enumerated(EnumType.STRING)
    private Category category;
* */
public record BookDto(int id, String title, double price, String author, String imgUrl,
                      LocalDate publisDate,String category) {


}
