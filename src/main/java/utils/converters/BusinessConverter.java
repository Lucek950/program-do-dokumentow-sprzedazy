package utils.converters;

import database.models.entitesModel.Business;
import modelFx.entitiesFX.businessFX.BusinessFX;

public class BusinessConverter {
    public static Business convertToBusiness(BusinessFX businessFX){
        Business business = new Business();
        business.setId(1);
        business.setName(businessFX.getName());
        business.setNip(businessFX.getNip());
        business.setTown(businessFX.getTown());
        business.setZc1(businessFX.getZc1());
        business.setZc2(businessFX.getZc2());
        business.setStreet(businessFX.getStreet());
        business.setAddress(businessFX.getAddress());
        business.setCountry(businessFX.getCountry());
        business.setTownPost(businessFX.getTownPost());
        business.setZcPost(businessFX.getZc1Post());
        business.setZc2Post(businessFX.getZc2Post());
        business.setStreetPost(businessFX.getStreetPost());
        business.setAddressPost(businessFX.getAddressPost());
        business.setCountryPost(businessFX.getCountryPost());
        business.setPostSame(businessFX.getPostSame());
        business.setPhone(businessFX.getPhone());
        business.setFax(businessFX.getFax());
        business.setEmail(businessFX.getEmail());
        business.setWebsite(businessFX.getWebsite());
        business.setBank1(businessFX.getBank1());
        business.setBank2(businessFX.getBank2());
        business.setBank3(businessFX.getBank3());
        business.setBank4(businessFX.getBank4());
        business.setBank5(businessFX.getBank5());
        business.setBank6(businessFX.getBank6());
        business.setBank7(businessFX.getBank7());
        return business;
    }

    public static BusinessFX convertToBusinessFX(Business business){
        BusinessFX businessFX = new BusinessFX();
        businessFX.setId(business.getId());
        businessFX.setName(business.getName());
        businessFX.setNip(business.getNip());
        businessFX.setTown(business.getTown());
        businessFX.setZc1(business.getZc1());
        businessFX.setZc2(business.getZc2());
        businessFX.setStreet(business.getStreet());
        businessFX.setAddress(business.getAddress());
        businessFX.setCountry(business.getCountry());
        businessFX.setPostSame(business.isPostSame());
        businessFX.setTownPost(business.getTownPost());
        businessFX.setZc1Post(business.getZcPost());
        businessFX.setZc2Post(business.getZc2Post());
        businessFX.setStreetPost(business.getStreetPost());
        businessFX.setAddressPost(business.getAddressPost());
        businessFX.setCountryPost(business.getCountryPost());
        businessFX.setPhone(business.getPhone());
        businessFX.setFax(business.getFax());
        businessFX.setEmail(business.getEmail());
        businessFX.setWebsite(business.getWebsite());
        businessFX.setBank1(business.getBank1());
        businessFX.setBank2(business.getBank2());
        businessFX.setBank3(business.getBank3());
        businessFX.setBank4(business.getBank4());
        businessFX.setBank5(business.getBank5());
        businessFX.setBank6(business.getBank6());
        businessFX.setBank7(business.getBank7());
        return businessFX;
    }
}
