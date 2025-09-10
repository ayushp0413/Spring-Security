package com.vector.engineering.learnspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponseDto {

        private String quote;
        private String author;
        private String category;

        public String getQuote() {
                return quote;
        }

        public void setQuote(String quote) {
                this.quote = quote;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        @Override
        public String toString() {
            return "\"" + quote + "\" — " + author + " [" + category + "]";
        }
}
