package com.vector.engineering.learnspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponseDto {

        private String quote;
        private String author;
        private String category;

        @Override
        public String toString() {
            return "\"" + quote + "\" — " + author + " [" + category + "]";
        }
}
