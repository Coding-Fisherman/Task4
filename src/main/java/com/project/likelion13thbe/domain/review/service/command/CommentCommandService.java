package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.review.converter.CommentConverter;
import com.project.likelion13thbe.domain.review.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.review.entity.Comment;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.CommentRepository;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public CommentResDTO.CommentCreateResDTO createComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO) {

        Member member = memberRepository.findById(commentCreateReqDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Review review = reviewRepository.findById(commentCreateReqDTO.reviewId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Comment comment = CommentConverter.toComment(commentCreateReqDTO, member, review);

        commentRepository.save(comment);

        return CommentConverter.toCommentCreateResDTO(comment);
    }
}
