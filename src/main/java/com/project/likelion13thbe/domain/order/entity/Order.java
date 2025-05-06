package com.project.likelion13thbe.domain.order.entity;

import com.project.likelion13thbe.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private Long totalPrice;

    @Column
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member member;
}
