package com.capgemini.jpa.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Server {

    @Id
    @SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ip;

    @Column
    @CreationTimestamp
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @Column
    private boolean deleted = Boolean.FALSE;

    @Version
    private Long version;

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

}
