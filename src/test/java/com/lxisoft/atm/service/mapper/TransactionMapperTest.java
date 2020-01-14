package com.lxisoft.atm.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TransactionMapperTest {

    private TransactionMapper transactionMapper;

    @BeforeEach
    public void setUp() {
        transactionMapper = new TransactionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(transactionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transactionMapper.fromId(null)).isNull();
    }
}
