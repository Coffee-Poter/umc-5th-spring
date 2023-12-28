package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberResponseDto;
import umc.study.web.dto.StoreResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDto.ChallengeMissionResultDto toChallengeMissionDto(MemberMission mission){
        return MemberResponseDto.ChallengeMissionResultDto.builder()
                .memberId(mission.getMember().getId())
                .missionId(mission.getMission().getId())
                .missionStatus(mission.getStatus())
                .updatedAt(mission.getCreatedAt())
                .build();
    }

    public static MemberResponseDto.ReviewPreViewDto reviewPreViewDto(Review review){
        return MemberResponseDto.ReviewPreViewDto.builder()
                .storename(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static MemberResponseDto.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList){
        List<MemberResponseDto.ReviewPreViewDto> reviewPreViewDtoList = reviewList.getContent().stream()
                .map(MemberConverter::reviewPreViewDto).collect(Collectors.toList());

        return MemberResponseDto.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDtoList.size())
                .reviewList(reviewPreViewDtoList)
                .build();
    }

    public static MemberResponseDto.MissionPreViewDto missionPreViewDto(MemberMission memberMission){
        if(MissionStatus.CHALLENGING.equals(memberMission.getStatus())) {
            Mission mission = memberMission.getMission();
            return MemberResponseDto.MissionPreViewDto.builder()
                    .storename(mission.getStore().getName())
                    .mission_spec(mission.getMissionSpec())
                    .reward(mission.getReward())
                    .deadLine(mission.getDeadline())
                    .createdAt(mission.getCreatedAt())
                    .build();
        }
        else
            return null;
    }

    public static MemberResponseDto.MissionPreViewListDto missionPreViewListDto(Page<MemberMission> missionList){
        List<MemberResponseDto.MissionPreViewDto> missionPreViewDtoList = missionList.getContent().stream()
                .map(MemberConverter::missionPreViewDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return MemberResponseDto.MissionPreViewListDto.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDtoList.size())
                .missionList(missionPreViewDtoList)
                .build();
    }

}
