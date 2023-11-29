package umc.study.service.StoreService;

import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDto;

public interface StoreCommandService {

    Store joinStore(StoreRequestDto.JoinDto request);

    Review createReview(Long memberId, Long storeId, StoreRequestDto.ReviewDto request);

    Mission createMission(Long storeId, StoreRequestDto.CreateMissionDto request);
}
