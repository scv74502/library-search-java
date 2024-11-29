package com.library;
import java.util.List;

public record Document(
        String title,
        List<String> author,
        String isbn,
        String publisher,
        String datetime
) {
}
