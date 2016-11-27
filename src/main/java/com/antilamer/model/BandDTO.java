package com.antilamer.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "BANDS")
public class BandDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENT_VERSION_ID")
    @Index(name = "U_CURRENT_VERSION_ID_IDX")
    private BandVersionDTO currentVersion;

    @OneToMany (mappedBy = "band")
    private Set<BandVersionDTO> versions;

    public BandDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BandVersionDTO getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(BandVersionDTO currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Set<BandVersionDTO> getVersions() {
        return versions;
    }

    public void setVersions(Set<BandVersionDTO> versions) {
        this.versions = versions;
    }
}
