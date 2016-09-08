package com.antilamer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BANDS")
public class BandDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    public BandDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
