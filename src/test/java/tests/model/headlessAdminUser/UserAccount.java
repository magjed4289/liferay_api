package tests.model.headlessAdminUser;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserAccount {
    public String alternateName;
    public String emailAddress;
    public String givenName;
    public String familyName;
}
