package com.hun.blog.service.sequence;

import com.hun.blog.domain.sequence.CustomSequences;

import javax.servlet.http.HttpSession;

public interface SequenceService {

	CustomSequences findByKey(String key);
}