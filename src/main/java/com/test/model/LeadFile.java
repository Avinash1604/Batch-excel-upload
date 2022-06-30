package com.test.model;


import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("leads")
public class LeadFile {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getTag() {
        return tag;
    }

    public String getComment() {
        return comment;
    }

    @ExcelCellName("First Name")
    private String firstName;

    @ExcelCellName("Last Name")
    private String lastName;

    @ExcelCellName("Email")
    private String email;

    @ExcelCellName("Phone")
    private String phone;

    @ExcelCellName("Message")
    private String message;

    @ExcelCellName("Lead Status")
    private String status;

    @ExcelCellName("Lead tags")
    private String tag;

    @ExcelCellName("Notes")
    private String comment;
}
