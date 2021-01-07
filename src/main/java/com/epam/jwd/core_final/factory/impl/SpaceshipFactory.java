package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {

    public static final SpaceshipFactory INSTANCE = new SpaceshipFactory();

    private SpaceshipFactory() {
    }

    @Override
    public Spaceship create(Object... args) {

        Spaceship spaceship;

        String name = (String) args[0];
        Long flightDistance = (Long) args[1];
        Map<Role, Short> crew = createMap((String) args[2]);

        spaceship = new Spaceship(name, flightDistance, crew);

        return spaceship;
    }

    private Map<Role, Short> createMap(String mapData) {
        Map<Role, Short> map = new HashMap<>();

        for (String roleAndItsQuantity : mapData.substring(1, mapData.length() - 1).split(",")) {
            String[] temp = roleAndItsQuantity.split(":");
            map.put(Role.resolveRoleById(Integer.parseInt(temp[0])), Short.parseShort(temp[1]));
        }
        return map;
    }
}
