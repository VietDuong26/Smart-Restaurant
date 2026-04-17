package com.example.SmartRestaurant.exception;

public class DuplicateDataException
        extends AppException {

    public DuplicateDataException(String field) {
        super(field + " đã tồn tại");
    }
}
