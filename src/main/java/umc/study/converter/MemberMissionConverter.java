package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

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

}
