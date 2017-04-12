package com.antilamer.dto.encoder;

import java.io.Serializable;

public class EncoderDTO implements Serializable {
    private String password;
    private String stringToEncode;
    private String encodedString;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStringToEncode() {
        return stringToEncode;
    }

    public void setStringToEncode(String stringToEncode) {
        this.stringToEncode = stringToEncode;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }
}
