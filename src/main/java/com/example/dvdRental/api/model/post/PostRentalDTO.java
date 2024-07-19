package com.example.dvdRental.api.model.post;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PostRentalDTO {
    String firstName;
    String lastName;
    String email;
    String filmTitle;
    String staffEmail;
    Integer storeId;
}
