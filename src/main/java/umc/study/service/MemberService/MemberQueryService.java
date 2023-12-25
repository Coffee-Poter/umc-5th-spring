package umc.study.service.MemberService;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long value);

    Optional<Mission> findMission(Long id);

    Optional<MemberMission> findMemberMission(Long missionId);
}
