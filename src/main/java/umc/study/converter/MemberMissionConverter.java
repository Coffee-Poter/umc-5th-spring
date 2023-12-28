package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member memeber, Mission mission, MissionStatus missionStatus){

        return MemberMission.builder()
                .member(memeber)
                .mission(mission)
                .status(missionStatus)
                .build();
    }

    public static MemberResponseDto.FinishMissionResultDto toFinishMission(MemberMission memberMission){

        return MemberResponseDto.FinishMissionResultDto.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .missionStatus(memberMission.getStatus())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }
}
