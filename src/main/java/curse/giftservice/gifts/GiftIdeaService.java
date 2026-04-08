package curse.giftservice.gifts;

import curse.giftservice.common.BadRequestException;
import curse.giftservice.common.NotFoundException;
import curse.giftservice.dto.CreateGiftRequest;
import curse.giftservice.dto.GiftIdeaDto;
import curse.giftservice.tags.TagEntity;
import curse.giftservice.tags.TagRepository;
import curse.giftservice.tags.UserTagWeightEntity;
import curse.giftservice.tags.UserTagWeightRepository;
import curse.giftservice.wishlist.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftIdeaService {

    private final GiftRepository giftRepository;
    private final TagRepository tagRepository;
    private final UserTagWeightRepository userTagWeightRepository;
    private final WishlistRepository wishlistRepository;

    @Transactional(readOnly = true)
    public List<GiftIdeaDto> getIdeas(Long userId, int limit) {
        if (limit <= 0 || limit > 100) {
            throw new BadRequestException("limit should be in range 1..100");
        }

        List<Long> excludedIds = wishlistRepository.findGiftIdsByUserId(userId);
        List<Long> tagIds = userTagWeightRepository.findRankedByUserId(userId).stream()
                .map(UserTagWeightEntity::getTag)
                .map(TagEntity::getTagId)
                .limit(5)
                .toList();

        List<GiftEntity> gifts = tagIds.isEmpty()
                ? giftRepository.findIdeasAll(excludedIds, excludedIds.isEmpty(), PageRequest.of(0, limit))
                : giftRepository.findIdeasByTagIds(tagIds, excludedIds, excludedIds.isEmpty(), PageRequest.of(0, limit));

        return gifts.stream()
                .map(g -> new GiftIdeaDto(g.getGiftsId(), g.getGiftName(), g.getGiftAvgPrice(), g.getTag() != null ? g.getTag().getTagName() : null))
                .collect(Collectors.toList());
    }

    @Transactional
    public GiftIdeaDto createGift(CreateGiftRequest request) {
        TagEntity tag = tagRepository.findByTagNameIgnoreCase(request.getTagName())
                .orElseGet(() -> {
                    TagEntity newTag = new TagEntity();
                    newTag.setTagName(request.getTagName().trim());
                    return tagRepository.save(newTag);
                });

        GiftEntity giftEntity = new GiftEntity();
        giftEntity.setGiftName(request.getGiftName().trim());
        giftEntity.setGiftAvgPrice(request.getGiftAvgPrice());
        giftEntity.setTag(tag);

        GiftEntity saved = giftRepository.save(giftEntity);
        if (saved.getGiftsId() == null) {
            throw new NotFoundException("Gift was not saved");
        }

        return new GiftIdeaDto(saved.getGiftsId(), saved.getGiftName(), saved.getGiftAvgPrice(), tag.getTagName());
    }

    @Transactional(readOnly = true)
    public GiftEntity getById(Long giftId) {
        return giftRepository.findById(giftId).orElseThrow(() -> new NotFoundException("Gift with id=" + giftId + " not found"));
    }
}
