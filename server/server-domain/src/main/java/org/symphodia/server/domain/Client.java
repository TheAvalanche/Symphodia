package org.symphodia.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.symphodia.server.domain.band.Band;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "CLIENT")
@NamedQueries({
        @NamedQuery(name = "Client.byUsername", query = "SELECT c FROM Client c WHERE c.username = :username")
})
@SequenceGenerator(name = "CLIENT_SEQ", sequenceName = "CLIENT_SEQ", initialValue = 200000)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", length = 255)
    @NotNull
    @Email
    private String username;

    @Column(name = "PASSWORD", length = 255)
    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "ROLE", length = 255)
    @NotNull
    @JsonIgnore
    private String role;

    @Column(name = "ROLE_GROUP", length = 255)
    @NotNull
    @JsonIgnore
    private String roleGroup;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CLIENT_BAND",
            joinColumns = @JoinColumn(name = "CLIENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "BAND_ID"))
    private List<Band> bands;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }
}
