package org.symphodia.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
@NamedQueries({
        @NamedQuery(name = "Property.getProperty", query = "SELECT p FROM Property p WHERE p.propertyKey = :propertyKey")
})
//TODO: add max length to column definition
public class Property {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROPERTY_KEY")
    private PropertyKey propertyKey;

    @Column(name = "VALUE")
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PropertyKey getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(PropertyKey propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
