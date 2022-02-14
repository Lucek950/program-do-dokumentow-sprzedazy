package utils.converters;

import database.models.entitesModel.Contractor;
import modelFx.entitiesFX.contractorFX.ContractorFX;

public class ContractorConverter {
    public static Contractor convertToContractor(ContractorFX contractorFX){
        Contractor contractor = new Contractor();
        contractor.setId(contractorFX.getId());
        contractor.setName(contractorFX.getName());
        contractor.setNip(contractorFX.getNip());
        contractor.setPesel(contractorFX.getPesel());
        contractor.setTown(contractorFX.getTown());
        contractor.setZc1(contractorFX.getZc1());
        contractor.setZc2(contractorFX.getZc2());
        contractor.setStreet(contractorFX.getStreet());
        contractor.setAddress(contractorFX.getAddress());
        contractor.setCountry(contractorFX.getCountry());
        contractor.setPostSame(contractorFX.getPostSame());
        contractor.setTownPost(contractorFX.getTownPost());
        contractor.setZcPost(contractorFX.getZc1Post());
        contractor.setZc2Post(contractorFX.getZc2Post());
        contractor.setStreetPost(contractorFX.getStreetPost());
        contractor.setAddressPost(contractorFX.getAddressPost());
        contractor.setCountryPost(contractorFX.getCountryPost());
        contractor.setPhone(contractorFX.getPhone());
        contractor.setFax(contractorFX.getFax());
        contractor.setEmail(contractorFX.getEmail());
        contractor.setWebsite(contractorFX.getWebsite());
        contractor.setBank1(contractorFX.getBank1());
        contractor.setBank2(contractorFX.getBank2());
        contractor.setBank3(contractorFX.getBank3());
        contractor.setBank4(contractorFX.getBank4());
        contractor.setBank5(contractorFX.getBank5());
        contractor.setBank6(contractorFX.getBank6());
        contractor.setBank7(contractorFX.getBank7());
        return contractor;
    }

    public static ContractorFX convertToContractorFX(Contractor contractor){
        ContractorFX contractorFX = new ContractorFX();
        contractorFX.setId(contractor.getId());
        contractorFX.setName(contractor.getName());
        contractorFX.setNip(contractor.getNip());
        contractorFX.setPesel(contractor.getPesel());
        contractorFX.setTown(contractor.getTown());
        contractorFX.setZc1(contractor.getZc1());
        contractorFX.setZc2(contractor.getZc2());
        contractorFX.setStreet(contractor.getStreet());
        contractorFX.setAddress(contractor.getAddress());
        contractorFX.setCountry(contractor.getCountry());
        contractorFX.setPostSame(contractor.isPostSame());
        contractorFX.setTownPost(contractor.getTownPost());
        contractorFX.setZc1Post(contractor.getZcPost());
        contractorFX.setZc2Post(contractor.getZc2Post());
        contractorFX.setStreetPost(contractor.getStreetPost());
        contractorFX.setAddressPost(contractor.getAddressPost());
        contractorFX.setCountryPost(contractor.getCountryPost());
        contractorFX.setPhone(contractor.getPhone());
        contractorFX.setFax(contractor.getFax());
        contractorFX.setEmail(contractor.getEmail());
        contractorFX.setWebsite(contractor.getWebsite());
        contractorFX.setBank1(contractor.getBank1());
        contractorFX.setBank2(contractor.getBank2());
        contractorFX.setBank3(contractor.getBank3());
        contractorFX.setBank4(contractor.getBank4());
        contractorFX.setBank5(contractor.getBank5());
        contractorFX.setBank6(contractor.getBank6());
        contractorFX.setBank7(contractor.getBank7());
        return contractorFX;
    }
}
