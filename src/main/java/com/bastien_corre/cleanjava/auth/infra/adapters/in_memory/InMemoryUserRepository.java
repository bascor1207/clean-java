package com.bastien_corre.cleanjava.auth.infra.adapters.in_memory;

import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.domain.model.User;
import com.bastien_corre.cleanjava.core.infra.adapters.in_memory.InMemoryBaseRepository;

public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {}
