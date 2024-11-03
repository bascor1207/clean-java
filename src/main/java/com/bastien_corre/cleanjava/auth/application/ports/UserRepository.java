package com.bastien_corre.cleanjava.auth.application.ports;

import com.bastien_corre.cleanjava.auth.domain.model.User;
import com.bastien_corre.cleanjava.core.infra.adapters.BaseRepository;

public interface UserRepository extends BaseRepository<User> {}
