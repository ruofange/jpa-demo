package com.example.jpademo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TagId implements java.io.Serializable {
    private static final long serialVersionUID = -1211637030832846030L;
    @Column(name = "OwnerId", nullable = false, length = 32)
    private String ownerId;

    @Column(name = "TagKey", nullable = false, length = 100)
    private String tagKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TagId entity = (TagId) o;
        return Objects.equals(this.ownerId, entity.ownerId) &&
                Objects.equals(this.tagKey, entity.tagKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, tagKey);
    }

}