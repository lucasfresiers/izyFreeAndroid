package com.example.lucas.izyfree_mobile;


import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;
/**
 * Created by bertins on 22/03/18.
 */

public class Freelance {

        private static Freelance anonymous = new Freelance(-1, "Anonymous", "anonym");
        private String firstname;
        private String name;
        private int id = 0;
        private String email;
        private String password;
        private String phone;
        private String job;
        private String photo;
        private String cv;
        private List<String> mots;
        private String passwdHash;
        private String salt;
        private String search;

        public Freelance(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Freelance(int id, String name, String firstname) {
            this.id = id;
            this.name = name;
            this.setFirstName(firstname);
        }

        public Freelance() {
        }

        public static Freelance getAnonymousFreelance() {
            return anonymous;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
            return id + ": " + firstname + ", " + name + " <" + email + ">, TEL: " + phone + " / " + job;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public boolean isInFreelanceGroup() {
            return !(id == anonymous.getId());
        }

        public boolean isAnonymous() {
            return this.getId() == getAnonymousFreelance().getId();
        }

        public String getSearch() {
            search = name + " " + firstname + " " + email;
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public String getFirstName() {
            return firstname;
        }

        public void setFirstName(String firstname) {
            this.firstname = firstname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCv() {
            return cv;
        }

        public void setCv(String cv) {
            this.cv = cv;
        }

        public List<String> getMots() {
            return mots;
        }

        public void setMots(List<String> mots) {
            this.mots = mots;
        }
    }

