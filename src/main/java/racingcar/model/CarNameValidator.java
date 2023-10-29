package racingcar.model;

import java.util.ArrayList;

import racingcar.Rule;
import racingcar.Util;

public class CarNameValidator {
    private final String NAMES_STRING;
    public final ArrayList<String> NAMES;

    public CarNameValidator(String namesString) {
        this.NAMES_STRING = namesString;
        this.NAMES = Util.toArrayList(namesString);
        validate();
    }

    public void validate() {
        isRightNamesString();
        isNotDuplicate();
        isRightNameSize();
    }

    public void isRightNamesString() {
        // String 형식 확인: 앞&뒤-문자, 중간-문자&구분자
        if (!Rule.namesStringPattern.matcher(NAMES_STRING).matches()) {
            throw new IllegalArgumentException(Rule.CAR_NAME_WRONG_ERROR);
        }
    }

    public void isRightNameSize() {
        if (NAMES.stream().anyMatch(name -> name.isEmpty() || name.length() > Rule.MIN_CARNAMESIZE)) {
            throw new IllegalArgumentException(Rule.CAR_NAME_SIZE_ERROR);
        }
    }

    public void isNotDuplicate() {
        if (NAMES.size() != NAMES.stream().distinct().count()) {
            throw new IllegalArgumentException(Rule.CAR_NAME_DUPLICATE_ERROR);
        }
    }
}