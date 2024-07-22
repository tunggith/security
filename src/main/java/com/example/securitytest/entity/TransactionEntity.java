package com.example.securitytest.entity;

import com.example.securitytest.contanst.SecurityContanst;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history_transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SecurityContanst.ENTITY_COLUMN_ID)
    private Integer id;
    @Column(name = SecurityContanst.ENTITY_COLUMN_TRANSACTION_ID)
    private String transactionId;
    @Column(name = SecurityContanst.ENTITY_COLUMN_ACCOUNT_TRANSACTION)
    private String accountTransaction;
    @Column(name = SecurityContanst.ENTITY_COLUMN_INDEBT)
    private BigDecimal indebt;
    @Column(name = SecurityContanst.ENTITY_COLUMN_HAVE)
    private String have;
    @Column(name = SecurityContanst.ENTITY_COLUMN_TIME_TRANSACTION)
    @Temporal(TemporalType.DATE)
    private Date timeTransaction;
}
