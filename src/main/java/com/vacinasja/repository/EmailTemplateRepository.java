package com.vacinasja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinasja.model.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
	public Optional<EmailTemplate> findByName(String name);
}
