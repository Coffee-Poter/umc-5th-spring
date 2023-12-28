package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.validation.anotation.CheckPage;
import umc.study.validation.anotation.ExistMember;
import umc.study.validation.anotation.ExistStore;
import umc.study.validation.anotation.IsChallenging;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberResponseDto;
import umc.study.web.dto.StoreResponseDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{memberId}/missions/{missionId}/challeng")
    public ApiResponse<MemberResponseDto.ChallengeMissionResultDto> challengeMission(
            @ExistMember @PathVariable Long memberId,
            @IsChallenging @PathVariable Long missionId,
            @RequestBody @Valid MemberRequestDto.ChallengeMissionDto request){

        MemberMission mission = memberCommandService.challengeMission(memberId, missionId, request);
        return ApiResponse.onSuccess(MemberConverter.toChallengeMissionDto(mission));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "작성한 리뷰 목록 조회 API",description = "사용자가 작상한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberResponseDto.ReviewPreViewListDto> getReviewList(
            @ExistMember @PathVariable Long memberId,
            @CheckPage @RequestParam(name="page") Integer page
    ){
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDto(reviewList));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "진행 중인 미션 목록 조회 API",description = "사용자가 진행 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberResponseDto.MissionPreViewListDto> getMissionList(
            @ExistMember @PathVariable Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page
    ){
        Page<MemberMission> missionList = memberQueryService.getMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.missionPreViewListDto(missionList));
    }

    @PostMapping("/{memberId}/missions/{missionId}/finish")
    public ApiResponse<MemberResponseDto.FinishMissionResultDto> finishMission(
            @ExistMember @PathVariable Long memberId,
            @PathVariable Long missionId,
            @RequestBody @Valid MemberRequestDto.FinishMissionDto request
    ){
        MemberMission mission = memberCommandService.finishMission(memberId, missionId, request);
        return ApiResponse.onSuccess(MemberMissionConverter.toFinishMission(mission));
    }
}
