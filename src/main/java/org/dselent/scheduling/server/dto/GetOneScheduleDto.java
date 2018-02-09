package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;
import java.util.Objects;


public class GetOneScheduleDto {

    private final Integer id;
    private final Integer instructor_info_id;
    private final Integer section_info_id;


    @Generated("SparkTools")
    private GetOneScheduleDto(Builder builder) {
        // can add defaults if null for other places where the builder pattern is used

        this.id = builder.id;
        this.instructor_info_id = builder.instructor_info_id;
        this.section_info_id = builder.section_info_id;

        if (this.id == null) {
            throw new IllegalStateException("id cannot be null");
        }

        if (this.instructor_info_id == null) {
            throw new IllegalStateException("instructorInfoId cannot be null");
        }

        if (this.section_info_id == null) {
            throw new IllegalStateException("sectionInfoId cannot be null");
        }


    }


    public Integer getId() {
        return id;
    }

    public Integer getInstructor_info_id() {
        return instructor_info_id;
    }

    public Integer getSection_info_id() {
        return section_info_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetOneScheduleDto that = (GetOneScheduleDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(instructor_info_id, that.instructor_info_id) &&
                Objects.equals(section_info_id, that.section_info_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, instructor_info_id, section_info_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetOneScheduleDto{");
        sb.append("id=").append(id);
        sb.append(", instructor_info_id=").append(instructor_info_id);
        sb.append(", section_info_id=").append(section_info_id);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Creates builder to build {@link GetOneScheduleDto}.
     *
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder {

        private Integer id;
        private Integer instructor_info_id;
        private Integer section_info_id;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withInstructorInfoId(Integer instructorInfoId) {
            this.instructor_info_id = instructorInfoId;
            return this;
        }

        public Builder withSectionInfoId(Integer sectionInfoId) {
            this.section_info_id = sectionInfoId;
            return this;
        }

        public GetOneScheduleDto build() {
            return new GetOneScheduleDto(this);
        }
    }

}