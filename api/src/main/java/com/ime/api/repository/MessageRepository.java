package com.ime.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ime.api.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
