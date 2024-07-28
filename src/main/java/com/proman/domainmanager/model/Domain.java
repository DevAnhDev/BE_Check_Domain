package com.proman.domainmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="domain")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="domain_name")
    private String domanName;

    @Column(name="ip_address")
    private String ipAddress;

    @Column(name="active")
    private Boolean active = true;

    @Column(name="description")
    private String description;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "viettel_id")
    private Viettel viettel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_id")
    private Mobile mobile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vina_id")
    private Vina vina;


    public Vina getVina() {
        return vina;
    }

    public void setVina(Vina vina) {
        this.vina = vina;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Domain() {
    }

    public Domain(String domanName, String ipAddress, Boolean active, String description, Viettel viettel) {
        this.domanName = domanName;
        this.ipAddress = ipAddress;
        this.active = active;
        this.description = description;
        this.viettel = viettel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomanName() {
        return domanName;
    }

    public void setDomanName(String domanName) {
        this.domanName = domanName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Viettel getViettel() {
        return viettel;
    }

    public void setViettel(Viettel viettel) {
        this.viettel = viettel;
    }
}
