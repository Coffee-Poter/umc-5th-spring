package umc.study.service.MemberService;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberRequestDto;

public interface MemberCommandService {

    Member joinMember(MemberRequestDto.JoinDto request);

    MemberMission challengeMission(Long memberId, Long missionId, MemberRequestDto.ChallengeMissionDto request);

    MemberMission finishMission(Long memberId, Long missionId, MemberRequestDto.FinishMissionDto request);
}
