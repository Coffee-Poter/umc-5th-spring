package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.validation.anotation.ExistMember;
import umc.study.validation.anotation.IsChallenging;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberResponseDto;
import umc.study.web.dto.StoreResponseDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{memberId}/missions/{missionId}")
    public ApiResponse<MemberResponseDto.ChallengeMissionResultDto> challengeMission(
            @RequestBody @Valid MemberRequestDto.ChallengeMissionDto request,
            @ExistMember @PathVariable(name = "memberId") Long memberId,
            @IsChallenging @PathVariable(name = "missionId") Long missionId){

        MemberMission mission = memberCommandService.challengeMission(memberId, missionId, request);
        return ApiResponse.onSuccess(MemberConverter.toChallengeMissionDto(mission));
    }
}
