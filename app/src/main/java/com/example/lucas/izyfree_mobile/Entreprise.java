package com.example.lucas.izyfree_mobile;

/**
 * Created by bertins on 22/03/18.
 */

import java.security.Principal;
import java.security.SecureRandom;

public class Entreprise {
    private static Entreprise anonymous = new Entreprise(-1, "Anonymous", "anonym");
    private String name;
    private String nomContact;
    private String prenomContact;
    private String tel;
    private String email;
    private int id = 0;
    private String lienPhoto;
    private String password;
    private String passwdHash;
    private String salt;
    private String search;

    public Entreprise(int id, String nom) {
        this.id = id;
        this.name = nom;
    }

    public Entreprise(int id, String nom, String nomContact) {
        this.id = id;
        this.name = nom;
        this.nomContact = nomContact;
    }

    public Entreprise() {
    }

    public String getNomContact() {
        return nomContact;
    }

    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public String getPrenomContact() {
        return prenomContact;
    }

    public void setPrenomContact(String prenomContact) {
        this.prenomContact = prenomContact;
    }

    public static Entreprise getAnonymousUser() {
        return anonymous;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return this.password;
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    @Override
    public String toString() {
        return id + ": " + name + ", " + nomContact + " <" + email + ">";
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLienPhoto() {
        return lienPhoto;
    }

    public void setLienPhoto(String lienPhoto) {
        this.lienPhoto = lienPhoto;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isInUserGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousUser().getId();
    }

    public String getSearch() {
        search = name + ":" + nomContact + "->" + email;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}