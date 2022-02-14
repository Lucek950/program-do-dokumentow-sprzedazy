package database.models.entitesModel;

import com.j256.ormlite.field.DatabaseField;
import database.models.BaseModel;

public class Entities implements BaseModel {
    public Entities() { }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME")
    private String name;

    @DatabaseField(columnName = "NIP")
    private String nip;

    @DatabaseField(columnName = "TOWN")
    private String town;

    @DatabaseField(columnName = "ZC1")
    private String zc1;

    @DatabaseField(columnName = "ZC2")
    private String zc2;

    @DatabaseField(columnName = "STREET")
    private String street;

    @DatabaseField(columnName = "ADDRESS")
    private String address;

    @DatabaseField(columnName = "COUNTRY")
    private String country;

    @DatabaseField(columnName = "POST_SAME")
    private boolean postSame;

    @DatabaseField(columnName = "TOWN_POST")
    private String townPost;

    @DatabaseField(columnName = "ZC1_POST")
    private String zcPost;

    @DatabaseField(columnName = "ZC2_POST")
    private String zc2Post;

    @DatabaseField(columnName = "STREET_POST")
    private String streetPost;

    @DatabaseField(columnName = "ADDRESS_POST")
    private String addressPost;

    @DatabaseField(columnName = "COUNTRY_POST")
    private String countryPost;

    @DatabaseField(columnName = "PHONE")
    private String phone;

    @DatabaseField(columnName = "FAX")
    private String fax;

    @DatabaseField(columnName = "EMAIL")
    private String email;

    @DatabaseField(columnName = "WEBSITE")
    private String website;

    @DatabaseField(columnName = "BANK1")
    private String bank1;

    @DatabaseField(columnName = "BANK2")
    private String bank2;

    @DatabaseField(columnName = "BANK3")
    private String bank3;

    @DatabaseField(columnName = "BANK4")
    private String bank4;

    @DatabaseField(columnName = "BANK5")
    private String bank5;

    @DatabaseField(columnName = "BANK6")
    private String bank6;

    @DatabaseField(columnName = "BANK7")
    private String bank7;

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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZc1() {
        return zc1;
    }

    public void setZc1(String zc1) {
        this.zc1 = zc1;
    }

    public String getZc2() {
        return zc2;
    }

    public void setZc2(String zc2) {
        this.zc2 = zc2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isPostSame() {
        return postSame;
    }

    public void setPostSame(boolean postSame) {
        this.postSame = postSame;
    }

    public String getTownPost() {
        return townPost;
    }

    public void setTownPost(String townPost) {
        this.townPost = townPost;
    }

    public String getZcPost() {
        return zcPost;
    }

    public void setZcPost(String zcPost) {
        this.zcPost = zcPost;
    }

    public String getZc2Post() {
        return zc2Post;
    }

    public void setZc2Post(String zc2Post) {
        this.zc2Post = zc2Post;
    }

    public String getStreetPost() {
        return streetPost;
    }

    public void setStreetPost(String streetPost) {
        this.streetPost = streetPost;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public String getCountryPost() {
        return countryPost;
    }

    public void setCountryPost(String countryPost) {
        this.countryPost = countryPost;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBank1() {
        return bank1;
    }

    public void setBank1(String bank1) {
        this.bank1 = bank1;
    }

    public String getBank2() {
        return bank2;
    }

    public void setBank2(String bank2) {
        this.bank2 = bank2;
    }

    public String getBank3() {
        return bank3;
    }

    public void setBank3(String bank3) {
        this.bank3 = bank3;
    }

    public String getBank4() {
        return bank4;
    }

    public void setBank4(String bank4) {
        this.bank4 = bank4;
    }

    public String getBank5() {
        return bank5;
    }

    public void setBank5(String bank5) {
        this.bank5 = bank5;
    }

    public String getBank6() {
        return bank6;
    }

    public void setBank6(String bank6) {
        this.bank6 = bank6;
    }

    public String getBank7() {
        return bank7;
    }

    public void setBank7(String bank7) {
        this.bank7 = bank7;
    }
}
