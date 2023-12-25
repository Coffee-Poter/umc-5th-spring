package umc.study.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.validation.anotation.IsChallenging;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MissionStatusValidator implements ConstraintValidator<IsChallenging,Long> {

    private MemberQueryService memberQueryService;
    @Override
    public void initialize(IsChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<MemberMission> target = memberQueryService.findMemberMission(value);

        //target 즉 Optional<Mission>의 상태가 CHALLENGE가 아니어야
        if(target.get().getStatus() == MissionStatus.CHALLENGING){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGING.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
