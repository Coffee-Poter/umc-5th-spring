package umc.study.validation.anotation;

import umc.study.validation.validator.MissionStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionStatusValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER} )
@Retention(RetentionPolicy.RUNTIME)
public @interface IsChallenging {

    String message() default "해당 미션은 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
