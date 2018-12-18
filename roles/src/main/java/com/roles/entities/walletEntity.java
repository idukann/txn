package com.roles.entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.sql.Date;

@Entity
@Table(name="users_wallet")
@NamedQueries({
        @NamedQuery(
                name = "findUserById",
                query = "from User u where u.id_user = :id"
        ),
})
public class walletEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;



    @Column(name = "id_grade") private String grade;
    @Column(name = "id_limits") private String limits;
    @Column(name = "id_role") private String role;
    @Column(name = "id_user") private int idUser;
    @Column(name = "id_created_by") private int idCreatedBy;
    @Column(name = "id_modified_by") private int idModifiedBy;
    @Column(name = "date_created_on") private Date createdOn;
    @Column(name = "date_modified_on") private Date ModifiedOn;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCreatedBy() {
        return idCreatedBy;
    }

    public void setIdCreatedBy(int idCreatedBy) {
        this.idCreatedBy = idCreatedBy;
    }

    public int getIdModifiedBy() {
        return idModifiedBy;
    }

    public void setIdModifiedBy(int idModifiedBy) {
        this.idModifiedBy = idModifiedBy;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        ModifiedOn = modifiedOn;
    }
}