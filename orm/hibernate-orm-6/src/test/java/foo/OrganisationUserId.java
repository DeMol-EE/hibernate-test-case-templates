package foo;

import java.io.Serializable;
import java.util.Objects;

public class OrganisationUserId implements Serializable {

    private Integer organisation;
    private Integer user;

    public OrganisationUserId() {
    }

    public OrganisationUserId(Integer organisation, Integer user) {
        this.organisation = organisation;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationUserId that = (OrganisationUserId) o;
        return Objects.equals(organisation, that.organisation) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisation, user);
    }

    public Integer getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Integer organisation) {
        this.organisation = organisation;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
