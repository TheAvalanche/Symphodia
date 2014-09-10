package org.symphodia.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLIENT")
@SequenceGenerator(name = "CLIENT_SEQ", sequenceName = "CLIENT_SEQ", initialValue = 200000)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", length = 255)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", length = 255)
    @NotNull
    private String password;

    @Column(name = "ROLE", length = 255)
    @NotNull
    private String role;

    @Column(name = "ROLE_GROUP", length = 255)
    @NotNull
    private String roleGroup;

}
