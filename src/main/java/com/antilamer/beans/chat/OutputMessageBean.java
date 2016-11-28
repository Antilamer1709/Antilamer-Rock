package com.antilamer.beans.chat;

import java.util.Date;

public class OutputMessageBean extends MessageBean {
    private Date time;

    public OutputMessageBean(MessageBean original, Date time) {
        super(original.getId(), original.getMessage());
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
