package com.capgemini.jpa.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OptimisticLocking(type = OptimisticLockType.ALL)
@SQLDelete(sql="UPDATE server SET isActive=false WHERE id=?")
public class Server {

    @Id
    @SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ip;

    @Version
    private Long version;

    @Column
    private Boolean isActive = Boolean.TRUE;

    @Column
    @CreationTimestamp
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    public Server(String name, String ip) {
        super();
        this.name = name;
        this.ip = ip;
    }

    public Long getVersion(){
        return this.version;
    }

    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    public LocalDateTime getLastUpdateDate(){
        return this.lastUpdatedDate;
    }

    @PreRemove
    public void preRemove(){
        this.isActive = false;
    }

}
