package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    private Long id;
    private String name;

    public Criteria() {
    }

    private Criteria(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return null;
    }

    public String getName() {
        return null;
    }

//    public static Builder builder() {
//        return new Builder();
//    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Criteria build() {
            return null;
        }
    }
}
