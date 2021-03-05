package com.example.hobbie.model.binding;

import com.example.hobbie.model.entities.enums.GenderEnum;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpBindingModel {

    private String username;
    private String fullName;
    private GenderEnum gender;
    private String email;
    private String password;
    private String confirmPassword;

    public SignUpBindingModel() {
    }

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols.")
    @NotBlank(message = "Username can not be empty.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    @NotNull
    @Pattern(regexp=".+@.+\\..+", message = "Email must be valid.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols.")
    @NotBlank(message = "Password can not be empty.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols.")
    @NotBlank(message = "Password can not be empty.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
