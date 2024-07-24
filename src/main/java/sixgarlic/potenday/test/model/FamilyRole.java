package sixgarlic.potenday.test.model;

import lombok.Getter;

@Getter
public enum FamilyRole {
    DAD("아빠"), MOM("엄마"), SON("아들"), DAUGHTER("딸");

    private final String role;

    FamilyRole(String role) {
        this.role = role;
    }
}
