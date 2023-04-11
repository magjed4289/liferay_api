package tests.steps.headlessAdminUser;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import tests.model.BaseModel;
import tests.model.headlessAdminUser.HeadlessAdminUserEndpoints;
import tests.model.headlessAdminUser.UserAccount;

import java.util.*;

public class HeadlessAdminUserSteps {
    final BaseModel baseModel;
    final HeadlessAdminUserEndpoints headlessAdminUserEndpoints;
    public final List<String> userAccountsIdsList;
    public final List<String> accountsIdsList;

    public HeadlessAdminUserSteps(BaseModel baseModel) {
        this.baseModel = baseModel;
        this.headlessAdminUserEndpoints = new HeadlessAdminUserEndpoints();
        this.accountsIdsList = new ArrayList<>();
        this.userAccountsIdsList = new ArrayList<>();
    }

    //@After
    public void deleteAccountsAndUserAccounts(){
        if(!userAccountsIdsList.isEmpty()){
            for(String userAccountId:userAccountsIdsList){
                baseModel.setResponse(headlessAdminUserEndpoints.deleteUserAccount(userAccountId));
                baseModel.checkResponseCode(204);
            }
        }
        if(!accountsIdsList.isEmpty()){
            for(String accountId:accountsIdsList){
                baseModel.setResponse(headlessAdminUserEndpoints.deleteAccount(accountId));

                baseModel.checkResponseCode(204);
            }
        }
    }

    @And("a new user is created")
    public void aNewUserIsCreated(Map<String, String> cucumberData) {
        baseModel.setResponse(headlessAdminUserEndpoints.createAccount(new Date().toString()));
        String accountId = baseModel.getResponse().then().extract().path("id").toString();
        baseModel.checkResponseCode(200);
        accountsIdsList.add(accountId);
        UserAccount userAccount = UserAccount.builder()
                .alternateName(cucumberData.get("alternateName"))
                .emailAddress(cucumberData.get("emailAddress"))
                .givenName(cucumberData.get("givenName"))
                .familyName(cucumberData.get("familyName"))
                .build();
        baseModel.setResponse(headlessAdminUserEndpoints.createUserAccount(accountId,userAccount));
        baseModel.checkResponseCode(200);
        userAccountsIdsList.add(baseModel.getResponse().then().extract().path("id").toString());
    }

    public void updateTheUser(Map<String, String> cucumberData){
        UserAccount userAccount = UserAccount.builder()
                .alternateName(cucumberData.get("alternateName"))
                .emailAddress(cucumberData.get("emailAddress"))
                .givenName(cucumberData.get("givenName"))
                .familyName(cucumberData.get("familyName"))
                .build();
        baseModel.setResponse(headlessAdminUserEndpoints.updateUserAccount(userAccountsIdsList.get(0),userAccount));
        baseModel.checkResponseCode(200);
    }
}
