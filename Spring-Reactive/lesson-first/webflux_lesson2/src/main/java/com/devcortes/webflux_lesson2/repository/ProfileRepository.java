package com.devcortes.webflux_lesson2.repository;

import com.devcortes.webflux_lesson2.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
}
