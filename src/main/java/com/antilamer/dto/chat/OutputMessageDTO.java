package com.antilamer.dto.chat;

import java.security.Principal;
import java.util.Date;

public class OutputMessageDTO extends MessageDTO {
    private Date time;
    private String user;

    public OutputMessageDTO(MessageDTO original, Date time, Principal principal) {
        super(original.getId(), original.getMessage());
        this.time = time;
        this.user = principal.getName();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
