package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.model.Role;

public interface RoleService {
    Role findByName(String name);
}
