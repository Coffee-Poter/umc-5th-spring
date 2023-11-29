package umc.study.web.dto;

import lombok.Getter;
import umc.study.domain.Region;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class StoreRequestDto {

    @Getter
    public static class JoinDto{
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;
        @NotNull
        Float score;
        @NotNull
        String regionName;
        @NotNull
        Long regionId;
    }

    @Getter
    public static class ReviewDto{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }

    @Getter
    public static class CreateMissionDto{
        Integer reward;
        String missionSpec;

    }
}
