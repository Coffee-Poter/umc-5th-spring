package umc.study.web.dto;

import lombok.Getter;
import umc.study.domain.Region;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoreRequestDto {

    @Getter
    public static class JoinDto{
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;
        @NotNull
        Float score;
        //검증하는 annotatoin 만들어서 넣기 @ExistRegion
        Long region;
    }
}
