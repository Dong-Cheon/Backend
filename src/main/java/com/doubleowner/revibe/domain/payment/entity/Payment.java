package com.doubleowner.revibe.domain.payment.entity;

import com.doubleowner.revibe.domain.execution.entity.Execution;
import com.doubleowner.revibe.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tossId; // 토스에서 별도로 관리하는 아이디

    @Enumerated(value = EnumType.STRING)
    private PayMethod payMethod;

    @Enumerated(value = EnumType.STRING)
    private PayStatus payStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_id")
    private Execution execution; //체결  아이디

}
