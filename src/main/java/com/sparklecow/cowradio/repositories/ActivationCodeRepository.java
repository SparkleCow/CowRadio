package com.sparklecow.cowradio.repositories;

import com.sparklecow.cowradio.entities.user.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Integer>{

    Optional<ActivationCode> findByCode(String code);
}
