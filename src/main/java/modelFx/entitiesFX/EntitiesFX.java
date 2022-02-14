package modelFx.entitiesFX;

import javafx.beans.property.*;

import java.io.Serializable;

public class EntitiesFX implements Serializable {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty nip = new SimpleStringProperty();
    private final StringProperty town = new SimpleStringProperty();
    private final StringProperty zc1 = new SimpleStringProperty();
    private final StringProperty zc2 = new SimpleStringProperty();
    private final StringProperty street = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final BooleanProperty postSame = new SimpleBooleanProperty();
    private final StringProperty townPost = new SimpleStringProperty();
    private final StringProperty zc1Post = new SimpleStringProperty();
    private final StringProperty zc2Post = new SimpleStringProperty();
    private final StringProperty streetPost = new SimpleStringProperty();
    private final StringProperty addressPost = new SimpleStringProperty();
    private final StringProperty countryPost = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty fax = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty website = new SimpleStringProperty();
    private final StringProperty bank1 = new SimpleStringProperty();
    private final StringProperty bank2 = new SimpleStringProperty();
    private final StringProperty bank3 = new SimpleStringProperty();
    private final StringProperty bank4 = new SimpleStringProperty();
    private final StringProperty bank5 = new SimpleStringProperty();
    private final StringProperty bank6 = new SimpleStringProperty();
    private final StringProperty bank7 = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNip() {
        return nip.get();
    }

    public StringProperty nipProperty() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip.set(nip);
    }

    public String getTown() {
        return town.get();
    }

    public StringProperty townProperty() {
        return town;
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getZc1() {
        return zc1.get();
    }

    public StringProperty zc1Property() {
        return zc1;
    }

    public void setZc1(String zc1) {
        this.zc1.set(zc1);
    }

    public String getZc2() {
        return zc2.get();
    }

    public StringProperty zc2Property() {
        return zc2;
    }

    public void setZc2(String zc2) {
        this.zc2.set(zc2);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public boolean getPostSame() {
        return postSame.get();
    }

    public BooleanProperty postSameProperty() {
        return postSame;
    }

    public void setPostSame(boolean postSame) {
        this.postSame.set(postSame);
    }

    public String getTownPost() {
        return townPost.get();
    }

    public StringProperty townPostProperty() {
        return townPost;
    }

    public void setTownPost(String townPost) {
        this.townPost.set(townPost);
    }

    public String getZc1Post() {
        return zc1Post.get();
    }

    public StringProperty zc1PostProperty() {
        return zc1Post;
    }

    public void setZc1Post(String zc1Post) {
        this.zc1Post.set(zc1Post);
    }

    public String getZc2Post() {
        return zc2Post.get();
    }

    public StringProperty zc2PostProperty() {
        return zc2Post;
    }

    public void setZc2Post(String zc2Post) {
        this.zc2Post.set(zc2Post);
    }

    public String getStreetPost() {
        return streetPost.get();
    }

    public StringProperty streetPostProperty() {
        return streetPost;
    }

    public void setStreetPost(String streetPost) {
        this.streetPost.set(streetPost);
    }

    public String getAddressPost() {
        return addressPost.get();
    }

    public StringProperty addressPostProperty() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost.set(addressPost);
    }

    public String getCountryPost() {
        return countryPost.get();
    }

    public StringProperty countryPostProperty() {
        return countryPost;
    }

    public void setCountryPost(String countryPost) {
        this.countryPost.set(countryPost);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getFax() {
        return fax.get();
    }

    public StringProperty faxProperty() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax.set(fax);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getWebsite() {
        return website.get();
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public void setWebsite(String website) {
        this.website.set(website);
    }

    public String getBank1() {
        return bank1.get();
    }

    public StringProperty bank1Property() {
        return bank1;
    }

    public void setBank1(String bank1) {
        this.bank1.set(bank1);
    }

    public String getBank2() {
        return bank2.get();
    }

    public StringProperty bank2Property() {
        return bank2;
    }

    public void setBank2(String bank2) {
        this.bank2.set(bank2);
    }

    public String getBank3() {
        return bank3.get();
    }

    public StringProperty bank3Property() {
        return bank3;
    }

    public void setBank3(String bank3) {
        this.bank3.set(bank3);
    }

    public String getBank4() {
        return bank4.get();
    }

    public StringProperty bank4Property() {
        return bank4;
    }

    public void setBank4(String bank4) {
        this.bank4.set(bank4);
    }

    public String getBank5() {
        return bank5.get();
    }

    public StringProperty bank5Property() {
        return bank5;
    }

    public void setBank5(String bank5) {
        this.bank5.set(bank5);
    }

    public String getBank6() {
        return bank6.get();
    }

    public StringProperty bank6Property() {
        return bank6;
    }

    public void setBank6(String bank6) {
        this.bank6.set(bank6);
    }

    public String getBank7() {
        return bank7.get();
    }

    public StringProperty bank7Property() {
        return bank7;
    }

    public void setBank7(String bank7) {
        this.bank7.set(bank7);
    }
}
