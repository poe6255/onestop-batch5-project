package com.example.bookshop;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private  final BookDao bookDao;

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

    @Bean  @Transactional // @Profile("test")
    public ApplicationRunner runner(){
        return  r->{
            List.of(

                    new Book("England History",35.5,"Macaulary","https://source.unsplash.com/366x200/?sunset",
                            LocalDate.of(1957,3,21), Category.HISTORICAL),
                    new Book("World History",35.5,"H.G Wells","https://source.unsplash.com/366x200/?rain",
                            LocalDate.of(1957,3,21), Category.HISTORICAL),
                    new Book(" Return of The Native",35.5,"ThomasHardy","https://source.unsplash.com/366x200/?flowers",
                            LocalDate.of(1870,3,21), Category.NOVEL),
                    new Book("A Tale of Two Cities",35.5,"Charles Dickens","https://source.unsplash.com/366x200/?rain",
                            LocalDate.of(1859,3,21), Category.HISTORICAL),
                    new Book("Sherlock Holmes",35.5,"Arthur Conan Doyle","https://source.unsplash.com/366x200/?tree",
                            LocalDate.of(1859,3,21), Category.NOVEL),
                    new Book("The Love Hypothesis ",35.5,"Ali Hazelwood","https://source.unsplash.com/366x200/?food",
                            LocalDate.of(1859,3,21), Category.ROMANCE),
                    new Book("The Haunting of House",35.5,"Shirley Jackson","https://source.unsplash.com/366x200/?sky",
                            LocalDate.of(1959,3,21), Category.HORROR),
                    new Book("Fractal Noice",35.5,"Christopher Paolini","https://source.unsplash.com/366x200/?snow",
                            LocalDate.of(1959,3,21), Category.SIFI),
                    new Book("Fractal Noice",35.5,"Christopher Paolini","https://source.unsplash.com/366x200/?ocean",
                            LocalDate.of(1959,3,21), Category.SIFI)



            ).forEach(bookDao::save);
        };
    }
}
